package com.graduate.recruitment.controller.auth;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.dto.NhaTruongDangKyDto;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import com.graduate.recruitment.service.AuthService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;

@Controller
@AllArgsConstructor
public class NhaTruongAuthController {

    private NhaTruongRepository nhaTruongRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    @GetMapping("/nha-truong/dang-nhap")
    public String getTrangDangNhap(){
        return "/school/auth/login";
    }

    @GetMapping("/nha-truong/dang-ky")
    public String getTrangDangKy(Model model){
        model.addAttribute("nhaTruong", new NhaTruongDangKyDto());
        return "/school/auth/register";
    }

    @PostMapping("/nha-truong/dang-nhap")
    public String xuLyDangNhap(@RequestParam String email,
                               @RequestParam String password,
                               HttpServletRequest request,
                               Model model) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(email);

        if (taiKhoan == null) {
            model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác");
            return "/school/auth/login";
        }

        if (!passwordEncoder.matches(password, taiKhoan.getMatKhau())) {
            model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác");
            return "/school/auth/login";
        }

        NhaTruong nhaTruong = nhaTruongRepository.findByTaiKhoan(taiKhoan);

        // Lưu thông tin đăng nhập vào SecurityContext
        CustomUserPrincipal principal = new CustomUserPrincipal(taiKhoan, nhaTruong, Collections.emptyList());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(principal, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 🔥 Phần quan trọng: lưu vào session
        HttpSession session = request.getSession(true); // tạo session nếu chưa có
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return "redirect:/nha-truong/thong-tin";
    }

    @PostMapping("/nha-truong/dang-ky")
    public String xuLyDangKy(@ModelAttribute("nhaTruong") NhaTruongDangKyDto nhaTruongDangKyDto,
                             RedirectAttributes redirectAttributes){
        try{
            authService.nhaTruongDangKy(nhaTruongDangKyDto);
            redirectAttributes.addFlashAttribute("successMsg","Tài khoản của bạn đang chờ phê duyệt, hãy theo dõi email!");
            return "redirect:/nha-truong/dang-nhap";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/nha-truong/dang-ky";
        }
    }
}
