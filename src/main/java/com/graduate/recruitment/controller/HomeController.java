package com.graduate.recruitment.controller;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.repository.*;
import com.graduate.recruitment.service.DoanhnghiepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class HomeController {

    private DanhMucRepository danhMucRepository;
    private BaiDangRepository baiDangRepository;
    private DoanhNghiepRepository doanhNghiepRepository;
    private KyNangRepository kyNangRepository;
    private DoanhnghiepService doanhnghiepService;
    private SinhVienRepository sinhVienRepository;
    private NhaTruongRepository nhaTruongRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("danhMucList",danhMucRepository.findAll());
        model.addAttribute("baiDangList",baiDangRepository.findAll().stream()
                .filter(bd -> bd.getTrangThai().name().equals("CON_HAN"))
                .sorted(Comparator.comparing(BaiDang::getTaoVaoLuc).reversed())
                .limit(4)
                .collect(Collectors.toList()));
        model.addAttribute("doanhNghiepList",doanhNghiepRepository.findAll());
        model.addAttribute("kyNangList",kyNangRepository.findAll());
        model.addAttribute("sinhVienCount",sinhVienRepository.count());
        model.addAttribute("nhaTruongCount",nhaTruongRepository.count());
        return "student/home/index";
    }

}
