package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.service.DoanhnghiepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BusinessController {
    private DoanhnghiepService doanhnghiepService;
    @GetMapping("/doanh-nghiep")
    public String home(Model model) {
        model.addAttribute("doanhNghiep",doanhnghiepService.getDoanhNghiepById("DN001"));
        return "business/home";
    }
}
