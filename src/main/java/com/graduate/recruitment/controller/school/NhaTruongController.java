package com.graduate.recruitment.controller.school;

import com.graduate.recruitment.service.NhaTruongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class NhaTruongController {
    private NhaTruongService nhaTruongService;
    @GetMapping("nha-truong/thong-tin")
    public String getNhaTruong(Model model){
        model.addAttribute("nhaTruong",nhaTruongService.getNhaTruong("NT001"));
        return "school/home";
    }
}
