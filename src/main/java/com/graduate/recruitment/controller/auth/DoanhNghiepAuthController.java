package com.graduate.recruitment.controller.auth;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.dto.DoanhNghiepDangKyDto;
import com.graduate.recruitment.dto.NhaTruongDangKyDto;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
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
public class DoanhNghiepAuthController {
    private final DoanhNghiepRepository doanhNghiepRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    @GetMapping("/doanh-nghiep/dang-nhap")
    public String getTrangDangNhap() {
        return "/business/auth/login";
    }

    @GetMapping("doanh-nghiep/dang-ky")
    public String getTrangDangKy(Model model) {
        model.addAttribute("doanhNghiep",new DoanhNghiepDangKyDto());
        return "/business/auth/register";
    }

    @PostMapping("/doanh-nghiep/dang-ky")
    public String xuLyDangKy(@ModelAttribute("doanhNghiep") DoanhNghiepDangKyDto doanhNghiepDangKyDto,
                             RedirectAttributes redirectAttributes){
        try{
            authService.doanhNghiepDangKy(doanhNghiepDangKyDto);
            redirectAttributes.addFlashAttribute("successMsg","Tài khoản của bạn đang chờ phê duyệt, hãy theo dõi email!");
            return "redirect:/doanh-nghiep/dang-nhap";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/doanh-nghiep/dang-ky";
        }
    }

    @PostMapping("/doanh-nghiep/dang-nhap")
    public String xuLyDangNhap(@RequestParam String email,
                               @RequestParam String password,
                               HttpServletRequest request,
                               Model model) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(email);

        if (taiKhoan == null) {
            model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác");
            return "/business/auth/login";
        }

        if (!passwordEncoder.matches(password, taiKhoan.getMatKhau())) {
            model.addAttribute("errorMsg", "Thông tin đăng nhập không chính xác");
            return "/business/auth/login";
        }

        DoanhNghiep doanhNghiep = doanhNghiepRepository.findByTaiKhoan(taiKhoan);

        // Lưu thông tin đăng nhập vào SecurityContext
        CustomUserPrincipal principal = new CustomUserPrincipal(taiKhoan, doanhNghiep, Collections.emptyList());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(principal, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 🔥 Phần quan trọng: lưu vào session
        HttpSession session = request.getSession(true); // tạo session nếu chưa có
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return "redirect:/doanh-nghiep";
    }
}
