package com.graduate.recruitment.controller;

import com.graduate.recruitment.service.DoanhnghiepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class DoanhNghiepController {
    private DoanhnghiepService doanhnghiepService;
    @GetMapping("/sinh-vien/doanh-nghiep/{id}")
    public String index(Model model, @PathVariable String id){
        model.addAttribute("doanhNghiep",doanhnghiepService.getDoanhNghiepById(id));
        return "student/company/index";
    }
}
