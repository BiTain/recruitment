package com.graduate.recruitment.controller;

import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.repository.KyNangRepository;
import com.graduate.recruitment.service.KyNangService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class KyNangController {
    private KyNangService kyNangService;

    @GetMapping("/sinh-vien/ky-nang")
    public String getAllKyNang(Model model,
                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "limit", defaultValue = "8") Integer limit){
        Page<KyNang> kyNangs = kyNangService.getAllKyNang(page, limit);
        model.addAttribute("kyNangList",kyNangs);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", kyNangs.getTotalPages());
        model.addAttribute("totalItems", kyNangs.getTotalElements());
        return "student/skill/list";
    }


}
