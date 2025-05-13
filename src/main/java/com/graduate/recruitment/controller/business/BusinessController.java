package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.service.DoanhnghiepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BusinessController {
    private DoanhnghiepService doanhnghiepService;
    private DoanhNghiepRepository doanhNghiepRepository;
    @GetMapping("/doanh-nghiep")
    public String home(Model model) {
        model.addAttribute("doanhNghiep",doanhnghiepService.getDoanhNghiepById("DN001"));
        return "business/home";
    }
}
