package com.graduate.recruitment.controller.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DoanhNghiepAuthController {
    @GetMapping("/doanh-nghiep/dang-nhap")
    public String getTrangDangNhap(){
        return "/business/auth/login";
    }

    @GetMapping("doanh-nghiep/dang-ky")
    public String getTrangDangKy(){
        return "/business/auth/register";
    }
}
