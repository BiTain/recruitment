package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.dto.LoiMoiThucTapDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.LoiMoiThucTap;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import com.graduate.recruitment.service.LoiMoiThucTapService;
import com.graduate.recruitment.service.business.ResumeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ResumeController {
    private final DoanhNghiepRepository doanhNghiepRepository;
    private ResumeService resumeService;
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;
    private LoiMoiThucTapService loiMoiThucTapService;
    private NhaTruongRepository nhaTruongRepository;
    private DoanhNghiepRepository danhNghiepRepository;

    @GetMapping("/doanh-nghiep/ho-so")
    public String getAllResume(Model model,
                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "limit", defaultValue = "1") Integer limit,
                               @RequestParam(value = "status", defaultValue = "dang-cho", required = false) String status,
                               @RequestParam(value = "maNhaTruong", defaultValue = "", required = false) String maNhaTruong,
                               @RequestParam(value = "maBaiDang", defaultValue = "", required = false) String maBaiDang,
                               @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                               @RequestParam(value = "sapXepBy", defaultValue = "", required = false) String sapXepBy
                               )
    {
        Page<SinhVienBaiDang> sinhVienBaiDangs = resumeService.getAllResumeByStatus(
                "DN001",
                status,
                page,
                limit,
                maNhaTruong,
                maBaiDang,
                keyword,
                sapXepBy
                );

        model.addAttribute("danhSachHoSo", sinhVienBaiDangs.getContent());
        model.addAttribute("status",status);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sinhVienBaiDangs.getTotalPages());
        model.addAttribute("totalItems", sinhVienBaiDangs.getTotalElements());
        model.addAttribute("nhaTruongs", nhaTruongRepository.findAllByTaiKhoan_TrangThaiIn(List.of(TrangThaiTaiKhoan.BI_KHOA, TrangThaiTaiKhoan.HOAT_DONG)));
        model.addAttribute("baiDangs", doanhNghiepRepository
                .findById("DN001")
                .get().getBaiDangs()
        );
        model.addAttribute("keyword", keyword);
        model.addAttribute("maBaiDang", maBaiDang);
        model.addAttribute("maNhaTruong", maNhaTruong);
        model.addAttribute("sort", sapXepBy);
        return "business/resume/list";
    }

    @PostMapping("/doanh-nghiep/ho-so/{maHoSo}")
    public String xacNhanHoSo(@PathVariable("maHoSo") String maHoSo,
                              RedirectAttributes redirectAttributes,
                              @RequestParam String ketQua){
        try {
            SinhVienBaiDang hoSo = sinhVienBaiDangRepository.findById(maHoSo)
                    .orElseThrow(()-> new EntityNotFoundException("Hồ sơ không tồn tại"));
            if (ketQua.equals("duyet")){
                hoSo.setKetQua(KetQua.THONG_QUA);
                redirectAttributes.addFlashAttribute("successMsg","Hồ sơ đã được thông qua");
            } else if (ketQua.equals("loai")) {
                hoSo.setKetQua(KetQua.TU_CHOI);
                redirectAttributes.addFlashAttribute("successMsg","Hồ sơ đã bị loại");
            }
            hoSo.setCapNhatVaoLuc(LocalDateTime.now());
            sinhVienBaiDangRepository.save(hoSo);
            return "redirect:/doanh-nghiep/ho-so";
        }catch (Exception e){
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/doanh-nghiep/ho-so";
        }
    }

    @PostMapping("/doanh-nghiep/loi-moi-thuc-tap")
    public String taoLoiMoiThucTap(RedirectAttributes redirectAttributes,
                                   @ModelAttribute LoiMoiThucTapDto loiMoiThucTapDto){
        try {
            LoiMoiThucTap loiMoiThucTap = loiMoiThucTapService.taoLMTT(loiMoiThucTapDto);
            if(loiMoiThucTap != null){
                redirectAttributes.addFlashAttribute("successMsg","Hồ sơ đã được thông qua");
                return "redirect:/doanh-nghiep/ho-so";
            }else {
                redirectAttributes.addFlashAttribute("errorMsg","Đã xảy ra lỗi");
                return "redirect:/doanh-nghiep/ho-so";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/doanh-nghiep/ho-so";
        }
    }

}
