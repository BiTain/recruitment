package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.dto.LichPhongVanDto;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.service.business.InterviewService;
import com.graduate.recruitment.service.business.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class InterviewController {
    private InterviewService interviewService;

    @PostMapping("/doanh-nghiep/lich-phong-van/tao")
    public String creatLichPhongVan(@ModelAttribute("lichPhongVan")LichPhongVanDto lichPhongVanDto){
        return "redirect:/doanh-nghiep/ho-so";
    }

    @GetMapping("/doanh-nghiep/lich-phong-van")
    public String getAllLichPhongVan(Model model,
                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                                     @RequestParam(value = "status", defaultValue = "dong-y", required = false)
                                     String status){
        model.addAttribute("lichPhongVanTrongNgay",interviewService.getLichPhongVanTrongNgay("DN001"));
        TrangThaiPhongVan trangThaiPV = switch (status) {
            case "dong-y" -> TrangThaiPhongVan.DONG_Y;
            case "hoan-thanh" -> TrangThaiPhongVan.HOAN_THANH;
            case "tu-choi" -> TrangThaiPhongVan.TU_CHOI;
            default -> null;
        };
        Page<LichPhongVan> lichPhongVans = interviewService.getAllLichPhongVan("DN001",page,limit);
        List<LichPhongVan> lichPhongVanList = lichPhongVans.getContent().stream()
                .filter(lpv->lpv.getTrangThai() == trangThaiPV)
                .collect(Collectors.toList());
        model.addAttribute("lichPhongVan",lichPhongVanList);
        return "business/schedule";
    }
}
