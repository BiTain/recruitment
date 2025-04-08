package com.graduate.recruitment.controller;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.service.DoanhnghiepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class DoanhNghiepController {
    private DoanhnghiepService doanhnghiepService;
    @GetMapping("/sinh-vien/doanh-nghiep/{id}")
    public String index(Model model, @PathVariable String id){
        DoanhNghiepDto doanhNghiepDto = doanhnghiepService.getDoanhNghiepById(id);
        List<BaiDangDto> baiDangDtos = doanhNghiepDto.getBaiDangs().stream()
                        .filter(baiDang -> Objects.equals(baiDang.getTrangThai(), "CON_HAN")).toList();
        model.addAttribute("doanhNghiep",doanhNghiepDto);
        model.addAttribute("baiDangs", baiDangDtos);
        return "student/company/index";
    }
}
