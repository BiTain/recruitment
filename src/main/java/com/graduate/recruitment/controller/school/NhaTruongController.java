package com.graduate.recruitment.controller.school;

import com.graduate.recruitment.dto.NhaTruongDto;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.mapper.NhaTruongMapper;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.service.FileService;
import com.graduate.recruitment.service.NhaTruongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class NhaTruongController {
    private NhaTruongService nhaTruongService;
    private NhaTruongRepository nhaTruongRepository;
    private FileService fileService;

    @GetMapping("nha-truong/thong-tin")
    public String getNhaTruong(Model model) {
        NhaTruong nt = nhaTruongService.getNhaTruong("NT001");
        model.addAttribute("nhaTruong", nt);
        model.addAttribute("nhaTruongDto", NhaTruongMapper.toDto(nt));
        return "school/home";
    }


    @PostMapping("nha-truong/thong-tin/update")
    public String updateNhaTruong(@ModelAttribute("nhaTruongDto") NhaTruongDto dto) {
        NhaTruong nt = nhaTruongService.getNhaTruong(dto.getMaNhaTruong());
        if(dto.getLogoFile() != null && !dto.getLogoFile().getOriginalFilename().isBlank()) {
           String strUrl = fileService.store(dto.getLogoFile());
           nt.setBieuTuong(strUrl);
        }
        nt.setTenTruong(dto.getTenTruong());
        nt.setDiaChi(String.format("%s, %s, %s", dto.getChiTietDiaChi(), dto.getXa(), dto.getHuyen()));
        nt.setSoDienThoai(dto.getSoDienThoai());
        nt.setMaSoThue(dto.getMaSoThue());
        nhaTruongRepository.save(nt);
        return "redirect:/nha-truong/thong-tin";
    }
}
