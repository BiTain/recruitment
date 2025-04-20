package com.graduate.recruitment.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/ky-nang")
    public String skill() {
        return "admin/skill/list";
    }

    @GetMapping("/danh-muc")
    public String danhMuc() {
        return "admin/danh-muc/list";
    }

    @GetMapping("/nha-truong")
    public String nhaTruong() {
        return "admin/nha-truong/list";
    }

    @GetMapping("/sinh-vien")
    public String sinhVien() {
        return "admin/sinh-vien/list";
    }

    @GetMapping("/doanh-nghiep")
    public String doanhNghiep() {
        return "admin/doanh-nghiep/list";
    }

    @GetMapping("/bai-dang")
    public String baiDang() {
        return "admin/bai-dang/list";
    }
}
