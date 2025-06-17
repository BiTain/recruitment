package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.dto.LichPhongVanDto;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.LoiMoiThucTap;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.repository.LichPhongVanRepository;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import com.graduate.recruitment.service.business.InterviewService;
import com.graduate.recruitment.service.business.ResumeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class InterviewController {
    private InterviewService interviewService;
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;
    private LichPhongVanRepository lichPhongVanRepository;

    @PostMapping("/doanh-nghiep/lich-phong-van/tao")
    public String creatLichPhongVan(@ModelAttribute("lichPhongVan")LichPhongVanDto lichPhongVanDto){
        return "redirect:/doanh-nghiep/ho-so";
    }

    @GetMapping("/doanh-nghiep/lich-phong-van")
    public String getAllLichPhongVan(Model model,
                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                                     @RequestParam(value = "status", defaultValue = "dong-y", required = false)
                                     String status){
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DoanhNghiep currentDN = customUserPrincipal.getDoanhNghiep();
        model.addAttribute("lichPhongVanTrongNgay",interviewService.getLichPhongVanTrongNgay(currentDN.getMaDoanhNghiep()));
        TrangThaiPhongVan trangThaiPV = switch (status) {
            case "dong-y" -> TrangThaiPhongVan.DONG_Y;
            case "hoan-thanh" -> TrangThaiPhongVan.HOAN_THANH;
            case "tu-choi" -> TrangThaiPhongVan.TU_CHOI;
            default -> null;
        };

        Page<LichPhongVan> lichPhongVans = interviewService.getAllLichPhongVan(currentDN.getMaDoanhNghiep(),page,limit, trangThaiPV);

        Map<String, String> ketQuaMap = new HashMap<>();
        List<SinhVienBaiDang> svbdList = sinhVienBaiDangRepository.findAll();
        for (SinhVienBaiDang svbd : svbdList) {
            ketQuaMap.put(svbd.getMaSVBD(), svbd.getKetQua().name());
        }
        model.addAttribute("ketQuaMap", ketQuaMap);

        model.addAttribute("lichPhongVan", lichPhongVans.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", lichPhongVans.getTotalPages());
        model.addAttribute("status", status);
        return "business/schedule";
    }

    @PostMapping("/doanh-nghiep/lich-phong-van")
    public String taoLichPhongVan(RedirectAttributes redirectAttributes,
                                  @ModelAttribute LichPhongVanDto lichPhongVanDto){
        try {
            LichPhongVan lichPhongVan = interviewService.createLichPhongVan(lichPhongVanDto);
            SinhVienBaiDang hoSo = sinhVienBaiDangRepository.findById(lichPhongVanDto.getMaHoSo())
                    .orElseThrow(()->new EntityNotFoundException("Hồ sơ không tồn tại"));
            if(lichPhongVan != null){
                hoSo.setKetQua(KetQua.CHO_PHONG_VAN);
                hoSo.setCapNhatVaoLuc(LocalDateTime.now());
                sinhVienBaiDangRepository.save(hoSo);
                redirectAttributes.addFlashAttribute("successMsg","Lên lịch phỏng vấn thành công");
                return "redirect:/doanh-nghiep/ho-so";
            }else{
                redirectAttributes.addFlashAttribute("errorMsg","Lên lịch phỏng vấn thất bại");
                return "redirect:/doanh-nghiep/ho-so";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMsg","Lên lịch phỏng vấn thất bại");
            return "redirect:/doanh-nghiep/ho-so";
        }
    }

    @PostMapping("/doanh-nghiep/lich-phong-van/chinh-sua")
    public String chinhSuaLichPhongVan(RedirectAttributes redirectAttributes,
                                       @ModelAttribute LichPhongVanDto lichPhongVanDto){
        try {
            LichPhongVan lichPhongVan = interviewService.chinhSuaLichPhongVan(lichPhongVanDto);
            redirectAttributes.addFlashAttribute("successMsg", "Chỉnh sửa lịch phỏng vấn thành công");
            return "redirect:/doanh-nghiep/lich-phong-van";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/doanh-nghiep/lich-phong-van";
        }
    }

    @PostMapping("/doanh-nghiep/lich-phong-van/hoan-thanh")
    public String xacNhanHoanThanh(RedirectAttributes redirectAttributes,
                                   @RequestParam String maLichPhongVan){
        try {
            LichPhongVan lichPhongVan = lichPhongVanRepository.findById(maLichPhongVan)
                    .orElseThrow(()-> new EntityNotFoundException("Lịch phỏng vấn không tồn tại!"));
            if(lichPhongVan!=null){
                lichPhongVan.setTrangThai(TrangThaiPhongVan.HOAN_THANH);
                lichPhongVan.setCapNhatVaoLuc(LocalDateTime.now());
                lichPhongVanRepository.save(lichPhongVan);
                redirectAttributes.addFlashAttribute("successMsg", "Đã xác nhận hoàn thành buổi phỏng vấn!");
            }
            return "redirect:/doanh-nghiep/lich-phong-van";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/doanh-nghiep/lich-phong-van";
        }
    }
}
