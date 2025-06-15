package com.graduate.recruitment.controller;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.dto.TaiKhoanDto;
import com.graduate.recruitment.entity.*;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import com.graduate.recruitment.service.LichPhongVanService;
import com.graduate.recruitment.service.LoiMoiThucTapService;
import com.graduate.recruitment.service.SinhVienBaiDangService;
import com.graduate.recruitment.service.SinhVienService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ThongTinController {

    private SinhVienBaiDangService sinhVienBaiDangService;
    private LichPhongVanService lichPhongVanService;
    private SinhVienService sinhVienService;
    private LoiMoiThucTapService loiMoiThucTapService;
    private NhaTruongRepository nhaTruongRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/sinh-vien/bai-dang/da-ung-tuyen")
    public String showAllBaiDang(Model model) {

        model.addAttribute("sinhVienBaiDangs", sinhVienBaiDangService.getBaiDangApplied("SV001"));
        return "student/info/job";
    }

    @GetMapping("sinh-vien/lich-phong-van")
    public String showLichPhongVan(Model model,
                                   @RequestParam(value = "status", defaultValue = "sap-toi", required = false)
                                   String status) {
        Map<String, List<LichPhongVan>> lichPhongVanByTrangThai =
                lichPhongVanService.getAllLichPhongVanByTrangThai("SV001");

        Map<String, SinhVienBaiDang> sinhVienBaiDangMap = new HashMap<>();
        List<SinhVienBaiDang> sinhVienBaiDangs = sinhVienBaiDangRepository.findAll();
        for (SinhVienBaiDang sinhVienBaiDang : sinhVienBaiDangs) {
            sinhVienBaiDangMap.put(sinhVienBaiDang.getMaSVBD(), sinhVienBaiDang);
        }
        model.addAttribute("sinhVienBaiDangMap", sinhVienBaiDangMap);

        // Thêm dữ liệu vào model
        model.addAttribute("lichSapToi", lichPhongVanByTrangThai.get("sap-toi"));
        model.addAttribute("lichDangCho", lichPhongVanByTrangThai.get("dang-cho"));
        model.addAttribute("lichHoanThanh", lichPhongVanByTrangThai.get("hoan-thanh"));

        // Đếm số lượng phỏng vấn theo từng loại
        model.addAttribute("upcomingCount", lichPhongVanByTrangThai.get("sap-toi").size());
        model.addAttribute("waitingCount", lichPhongVanByTrangThai.get("dang-cho").size());
        model.addAttribute("completedCount", lichPhongVanByTrangThai.get("hoan-thanh").size());
        model.addAttribute("activeTab", status);
        return "student/info/interview";
    }

    @GetMapping("sinh-vien/loi-moi-thuc-tap")
    public String showLoiMoiThucTap(Model model,
                                    @RequestParam(value = "status", defaultValue = "dang-cho", required = false)
                                    String status) {
        Map<String, List<LoiMoiThucTap>> lmttByTrangThai =
                loiMoiThucTapService.getAllLoiMoiThucTapByTrangThai("SV001");

        model.addAttribute("lmttDangCho", lmttByTrangThai.get("dang-cho"));
        model.addAttribute("lmttChapNhan", lmttByTrangThai.get("chap-nhan"));
        model.addAttribute("lmttTuChoi", lmttByTrangThai.get("tu-choi"));

        model.addAttribute("activeTab", status);
        return "student/info/internship";

    }

    @GetMapping("sinh-vien/tong-quan")
    public String showInfoSinhVien(Model model) {
        SinhVien sinhVien = sinhVienService.getByMaTaiKhoan("TK026");
        model.addAttribute("danhSachNhaTruong", nhaTruongRepository.findAll());
        if (sinhVien == null) {
            model.addAttribute("sinhVien", new SinhVienDto());

        } else {
            model.addAttribute("sinhVien", sinhVienService.getByMaSinhVien(sinhVien.getMaSinhVien()));
        }
        return "student/info/student";
    }

    @PostMapping("/sinh-vien/tao")
    public String taoSinhVien(RedirectAttributes redirectAttributes,
                              @ModelAttribute SinhVienDto sinhVienDto) {
        try {

            SinhVien sinhVien = sinhVienService.saveSinhVien("TK026", sinhVienDto);
            if (sinhVien != null) {
                redirectAttributes.addFlashAttribute("successMsg", "Đã gửi thông tin xác thực thành công");
            } else {
                redirectAttributes.addFlashAttribute("errorMsg", "Có thông tin bị lỗi");
            }
            return "redirect:/sinh-vien/tong-quan";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/sinh-vien/tong-quan";
        }
    }

    @GetMapping("/sinh-vien/doi-mat-khau")
    public String getPageDoiMatKhau(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserPrincipal cup = (CustomUserPrincipal) authentication.getPrincipal();

        TaiKhoan tk = taiKhoanRepository.findByEmail(cup.getTaiKhoan().getEmail());
        model.addAttribute("tk", tk);
        return "student/info/change-password";
    }

    @PostMapping("/sinh-vien/doi-mat-khau")
    public String doiMatKhau(
            @RequestParam("maTaiKhoan") String maTaiKhoan,
            @RequestParam("matKhauHienTai") String matKhauHienTai,
            @RequestParam("matKhauMoi") String matKhauMoi,
            @RequestParam("nhapLaiMatKhauMoi") String nhapLaiMatKhauMoi,
            RedirectAttributes redirectAttributes
    ) {
        try {
            TaiKhoan taiKhoan = taiKhoanRepository.findById(maTaiKhoan)
                    .orElseThrow(() -> new EntityNotFoundException("Tài khoản không tồn tại"));
            if (!matKhauMoi.equals(nhapLaiMatKhauMoi)) {
                redirectAttributes.addFlashAttribute("errorMsg", "Mật khẩu nhập lại không chính xác");
                return "redirect:/sinh-vien/doi-mat-khau";
            }
            if (passwordEncoder.matches(matKhauHienTai, taiKhoan.getMatKhau())) {
                taiKhoan.setMatKhau(passwordEncoder.encode(matKhauMoi));
                taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
                taiKhoanRepository.save(taiKhoan);
                redirectAttributes.addFlashAttribute("successMsg", "Đã thay đổi mật khẩu thành công!");
            } else {
                redirectAttributes.addFlashAttribute("errorMsg", "Mật khẩu hiện tại không khớp");
            }
            return "redirect:/sinh-vien/doi-mat-khau";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/sinh-vien/doi-mat-khau";
        }
    }
}
