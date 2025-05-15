package com.graduate.recruitment.controller.school;

import com.graduate.recruitment.dto.NhaTruongDto;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.mapper.NhaTruongMapper;
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
    public String getNhaTruong(Model model) {
        NhaTruong nt = nhaTruongService.getNhaTruong("NT001");
        model.addAttribute("nhaTruong", nt);
        model.addAttribute("nhaTruongDto", NhaTruongMapper.toDto(nt));
        return "school/home";
    }
}
