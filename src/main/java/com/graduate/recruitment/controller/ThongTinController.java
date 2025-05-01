package com.graduate.recruitment.controller;

import com.graduate.recruitment.service.LichPhongVanService;
import com.graduate.recruitment.service.SinhVienBaiDangService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ThongTinController {

    private SinhVienBaiDangService sinhVienBaiDangService;
    private LichPhongVanService lichPhongVanService;
    @GetMapping("/sinh-vien/bai-dang/da-ung-tuyen")
    public String showAllBaiDang(Model model){
        model.addAttribute("sinhVienBaiDangs",sinhVienBaiDangService.getBaiDangApplied("SV001"));
        return "student/info/job";
    }

    @GetMapping("sinh-vien/lich-phong-van")
    public String showLichPhongVan(Model model,
                                   @RequestParam(value = "status", defaultValue = "DONG_Y", required = false)
                                   String status){

        model.addAttribute("lichPhongVans",lichPhongVanService.getAllLichPhongVan("SV001",status));
        return "student/info/interview";
    }
}
