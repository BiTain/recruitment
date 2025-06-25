package com.graduate.recruitment.controller.auth;

import com.graduate.recruitment.dto.SinhVienDangKyDto;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import com.graduate.recruitment.service.AuthService;
import com.graduate.recruitment.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@AllArgsConstructor
public class SinhVienAuthController {

    private AuthService authService;
    private TaiKhoanRepository taiKhoanRepository;
    private EmailService emailService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/sinh-vien/dang-nhap")
    public String getTrangDangNhap() {
        return "/student/auth/login";
    }

    @PostMapping("/sinh-vien/dang-nhap")
    public String xuLyDangNhap(@RequestParam String email,
                               @RequestParam String password,
                               HttpServletRequest request,
                               Model model) {
        TaiKhoan taiKhoan = authService.xuLyDangNhap(email, password);

        if(taiKhoan == null) {
            model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác ");
            return "/student/auth/login";
        }
        else if (taiKhoan.getTrangThai() == TrangThaiTaiKhoan.KHONG_HOAT_DONG || taiKhoan.getTrangThai() == TrangThaiTaiKhoan.BI_KHOA) {
            model.addAttribute("errorMsg", "Tài khoản đã bị khóa");
            return "/student/auth/login";
        }
        else {
            // 🔥 Phần quan trọng: lưu vào session
            HttpSession session = request.getSession(true); // tạo session nếu chưa có
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());
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

    @GetMapping("/sinh-vien/quen-mat-khau")
    public String getTrangQuenMatKhau(Model model) {
        return "/student/auth/forgot-password";
    }

    @PostMapping("/sinh-vien/quen-mat-khau")
    public String xuLyQuenMatKhau(@RequestParam("email") String email, Model model) {
        TaiKhoan tk = taiKhoanRepository.findByEmail(email);

        if (tk == null) {
            model.addAttribute("message", "Email không tồn tại trong hệ thống.");
        } else {
            // Gửi email đặt lại mật khẩu hoặc token reset
            // Ví dụ: tạo token, lưu DB, gửi link reset
            emailService.sendResetPasswordEmail(tk);
            model.addAttribute("message", "Hướng dẫn đặt lại mật khẩu đã được gửi qua email.");
        }

        return "/student/auth/forgot-password"; // tên view HTML
    }

    @GetMapping("/sinh-vien/dat-lai-mat-khau")
    public String showResetPasswordForm(@RequestParam("maTaiKhoan") String maTaiKhoan, Model model) {
        model.addAttribute("maTaiKhoan", maTaiKhoan);
        return "/student/auth/reset-password-form"; // tên file HTML form đổi mật khẩu
    }

    @PostMapping("/sinh-vien/dat-lai-mat-khau")
    public String datLaiMatKhau(
            @RequestParam("maTaiKhoan") String maTaiKhoan,
            @RequestParam("matKhauMoi") String matKhauMoi,
            @RequestParam("xacNhanMatKhauMoi") String xacNhanMatKhauMoi,
            RedirectAttributes redirectAttributes) {
        if (!matKhauMoi.equals(xacNhanMatKhauMoi)) {
            redirectAttributes.addFlashAttribute("errorMsg", "Mật khẩu nhập lại không chính xác");
            redirectAttributes.addAttribute("maTaiKhoan", maTaiKhoan);
            return "redirect:/sinh-vien/dat-lai-mat-khau";
        } else {
            TaiKhoan tk = taiKhoanRepository.findById(maTaiKhoan).get();
            tk.setMatKhau(passwordEncoder.encode(matKhauMoi));
            taiKhoanRepository.save(tk);
            redirectAttributes.addFlashAttribute("successMsg", "Đặt lại mật khẩu thành công");
            return "redirect:/sinh-vien/dang-nhap";
        }
    }

}