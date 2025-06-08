package com.graduate.recruitment.controller;

import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.repository.DanhMucRepository;
import com.graduate.recruitment.repository.KyNangRepository;
import com.graduate.recruitment.service.KyNangService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class KyNangController {
    private KyNangService kyNangService;
    private DanhMucRepository danhMucRepository;

    @GetMapping("/sinh-vien/ky-nang")
    public String getAllKyNang(Model model,
                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                               @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                               @RequestParam(value = "maDanhMuc", defaultValue = "", required = false) String maDanhMuc,
                               @RequestParam(value = "sapXepBy", defaultValue = "", required = false) String sapXepBy){
        Page<KyNang> kyNangs = kyNangService.getAllKyNang(page, limit,keyword,maDanhMuc,sapXepBy);
        List<DanhMuc> danhMucs =  danhMucRepository.findAll();
        model.addAttribute("kyNangList",kyNangs);
        model.addAttribute("danhMucs",danhMucs);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", kyNangs.getTotalPages());
        model.addAttribute("totalItems", kyNangs.getTotalElements());
        model.addAttribute("maDanhMuc",maDanhMuc);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sort", sapXepBy);
        return "student/skill/list";
    }


}
