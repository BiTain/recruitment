package com.graduate.recruitment.controller.auth;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.entity.enums.VaiTro;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;

@Controller
@AllArgsConstructor
public class AdminAuthController {

    private TaiKhoanRepository taiKhoanRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin/dang-nhap")
    public String getTrangDangNhap(){
        return "/admin/dang-nhap";
    }

    @PostMapping("/admin/dang-nhap")
    public String xuLyDangNhap(@RequestParam String email,
                               @RequestParam String matKhau,
                               HttpServletRequest request,
                               Model model){
        try {
            TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(email);
            if (taiKhoan == null) {
                model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác");
                return "/admin/dang-nhap";
            }

            if (!passwordEncoder.matches(matKhau, taiKhoan.getMatKhau())) {
                model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác");
                return "/admin/dang-nhap";
            }

            if(!taiKhoan.getVaiTro().equals(VaiTro.ADMIN)){
                model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác");
                return "/admin/dang-nhap";
            }

            // Lưu thông tin đăng nhập vào SecurityContext
            CustomUserPrincipal principal = new CustomUserPrincipal(taiKhoan, Collections.emptyList());
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(principal, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 🔥 Phần quan trọng: lưu vào session
            HttpSession session = request.getSession(true); // tạo session nếu chưa có
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());

            return "redirect:/admin/bai-dang";

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
