package com.graduate.recruitment.controller;

import com.graduate.recruitment.repository.BaiDangRepository;
import com.graduate.recruitment.repository.DanhMucRepository;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.KyNangRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private DanhMucRepository danhMucRepository;
    private BaiDangRepository baiDangRepository;
    private DoanhNghiepRepository doanhNghiepRepository;
    private KyNangRepository kyNangRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("danhMucList",danhMucRepository.findAll());
        model.addAttribute("baiDangList",baiDangRepository.findAll());
        model.addAttribute("doanhNghiepList",doanhNghiepRepository.findAll());
        model.addAttribute("kyNangList",kyNangRepository.findAll());
        return "student/home/index";
    }
}
