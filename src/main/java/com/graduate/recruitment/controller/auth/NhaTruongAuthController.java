package com.graduate.recruitment.controller.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class NhaTruongAuthController {

    @GetMapping("/nha-truong/dang-nhap")
    public String getTrangDangNhap(){
        return "/school/auth/login";
    }

    @GetMapping("/nha-truong/dang-ky")
    public String getTrangDangKy(){
        return "/school/auth/register";
    }
}
