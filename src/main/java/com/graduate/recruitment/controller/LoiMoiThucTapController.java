package com.graduate.recruitment.controller;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.entity.*;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.service.LoiMoiThucTapService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class LoiMoiThucTapController {
    private LoiMoiThucTapService loiMoiThucTapService;
    private NhaTruongRepository nhaTruongRepository;
    private DoanhNghiepRepository doanhNghiepRepository;

    @PostMapping("/sinh-vien/loi-moi-thuc-tap/xac-nhan")
    public String xacNhanLMTT(RedirectAttributes redirectAttributes,
                              @RequestParam("maLMTT") String maLMTT,
                              @RequestParam("trangThai") String trangThai){
        try {
            loiMoiThucTapService.chapNhanOrTuChoi(maLMTT, trangThai);
            if (trangThai.equals("chap-nhan")){
                redirectAttributes.addFlashAttribute("successMsg",
                        "Bạn đã xác nhận tham gia thực tập thành công!");
            }else if (trangThai.equals("tu-choi")){
                redirectAttributes.addFlashAttribute("successMsg",
                        "Bạn đã từ chối tham gia thực tập!");
            }
            return "redirect:/sinh-vien/loi-moi-thuc-tap";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/sinh-vien/loi-moi-thuc-tap";
        }
    }

    @GetMapping("/doanh-nghiep/sinh-vien")
    public String getSinhVienThucTap(Model model,
                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                                     @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                     @RequestParam(value = "viTri", required = false, defaultValue = "") String viTriThucTap,
                                     @RequestParam(value = "truong", required = false, defaultValue = "") String maNhaTruong){
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DoanhNghiep currentDN = customUserPrincipal.getDoanhNghiep();
        Page<LoiMoiThucTap> loiMoiThucTaps = loiMoiThucTapService.getSinhVienDongYThucTapTheoDoanhNghiep(page, limit, currentDN.getMaDoanhNghiep(), keyword, viTriThucTap, maNhaTruong);
        List<NhaTruong> nhaTruongs = nhaTruongRepository.findAll().stream()
                .filter(nhaTruong -> nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.HOAT_DONG)
                || nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.BI_KHOA))
                .toList();

        List<String> danhSachViTri = doanhNghiepRepository.findById(currentDN.getMaDoanhNghiep()).get()
                        .getBaiDangs().stream().map(BaiDang::getTieuDe).toList();
        model.addAttribute("loiMoiThucTaps",loiMoiThucTaps.getContent());
        model.addAttribute("nhaTruongs",nhaTruongs);
        model.addAttribute("danhSachViTri",danhSachViTri);
        model.addAttribute("selectedViTri", viTriThucTap);
        model.addAttribute("selectedTruong", maNhaTruong);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("totalPages", loiMoiThucTaps.getTotalPages());
        model.addAttribute("limit", limit);
        return "business/student";
    }
}
