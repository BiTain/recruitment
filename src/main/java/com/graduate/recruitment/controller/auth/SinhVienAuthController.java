package com.graduate.recruitment.controller.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class SinhVienAuthController {

    @GetMapping("/sinh-vien/dang-nhap")
    public String getTrangDangNhap(){
        return "/student/auth/login";
    }

    @GetMapping("sinh-vien/dang-ky")
    public String getTrangDangKy(){
        return "/student/auth/register";
    }
}
