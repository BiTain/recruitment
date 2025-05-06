package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.dto.LichPhongVanDto;
import com.graduate.recruitment.service.business.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class InterviewController {
    private ResumeService resumeService;

    @PostMapping("/doanh-nghiep/lich-phong-van/tao")
    public String creatLichPhongVan(@ModelAttribute("lichPhongVan")LichPhongVanDto lichPhongVanDto){
        return "redirect:/doanh-nghiep/ho-so";
    }

    @GetMapping("/doanh-nghiep/lich-phong-van")
    public String getAllLichPhongVan(Model model){
        return "business/schedule";
    }
}
