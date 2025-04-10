package com.graduate.recruitment.controller;

import com.graduate.recruitment.service.SinhVienBaiDangService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ThongTinController {

    private SinhVienBaiDangService sinhVienBaiDangService;
    @GetMapping("/sinh-vien/bai-dang/da-ung-tuyen")
    public String showAllBaiDang(Model model){
        model.addAttribute("sinhVienBaiDangs",sinhVienBaiDangService.getBaiDangApplied("SV001"));
        return "student/info/job";
    }
}
