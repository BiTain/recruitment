package com.graduate.recruitment.controller.auth;

import com.graduate.recruitment.dto.SinhVienDangKyDto;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SinhVienAuthController {

    private AuthService authService;

    @GetMapping("/sinh-vien/dang-nhap")
    public String getTrangDangNhap() {
        return "/student/auth/login";
    }

    @PostMapping("/sinh-vien/dang-nhap")
    public String xuLyDangNhap(@RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        boolean valid = authService.xuLyDangNhap(email, password);
        if(!valid) {
            model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác");
            return "/student/auth/login";
        }
        else {
            return "redirect:/";
        }
    }

    @GetMapping("/sinh-vien/dang-ky")
    public String getTrangDangKy(Model model) {
        model.addAttribute("sinhVien", new SinhVienDangKyDto());
        return "/student/auth/register";
    }

    @GetMapping("/kich-hoat")
    public String verify(@RequestParam("maTaiKhoan") String maTaiKhoan, Model model) {
        boolean valid = authService.kichHoatTaiKhoan(maTaiKhoan);
        if (valid) {
            model.addAttribute("successMsg", "Tài khoản đã được xác nhận thành công vui lòng đăng nhập.");
        } else {
            model.addAttribute("errorMsg", "Tài khoản không hợp lệ hoặc đã hết hạn.");
        }
        return "/student/auth/login";
    }

    @PostMapping("/sinh-vien/dang-ky")
    public String register(@ModelAttribute("sinhVien") SinhVienDangKyDto dto, Model model) {
        try {
            authService.sinhVienDangKy(dto);
            model.addAttribute("message", "Đăng ký thành công! Vui lòng kiểm tra email.");
            return "/student/auth/verify-email";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/student/auth/register";
        }
    }
}