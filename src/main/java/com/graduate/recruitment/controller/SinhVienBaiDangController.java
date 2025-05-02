package com.graduate.recruitment.controller;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.SinhVienBaiDangDto;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.service.BaiDangService;
import com.graduate.recruitment.service.SinhVienBaiDangService;
import com.graduate.recruitment.service.SinhVienService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SinhVienBaiDangController {
    private SinhVienBaiDangService sinhVienBaiDangService;
    private BaiDangService baiDangService;
    private SinhVienService sinhVienService;

    @GetMapping("/sinh-vien/ung-tuyen")
    public String showPageApply(Model model,
                                @RequestParam String maBaiDang){
        SinhVienBaiDangDto sinhVienBaiDangDto = new SinhVienBaiDangDto();
        BaiDangDto baiDangDto = baiDangService.getByMaBaiDang(maBaiDang);
        sinhVienBaiDangDto.setMaBaiDang(baiDangDto.getMaBaiDang());
        sinhVienBaiDangDto.setTieuDe(baiDangDto.getTieuDe());
        // temp
        SinhVienDto sinhVienDto = sinhVienService.getByMaSinhVien("SV001");
        sinhVienBaiDangDto.setMaSinhVien("SV001");
        sinhVienBaiDangDto.setHoVaTen(sinhVienDto.getHoVaTen());
        sinhVienBaiDangDto.setSoDienThoai(sinhVienDto.getSoDienThoai());
        sinhVienBaiDangDto.setEmail(sinhVienDto.getEmail());
        model.addAttribute("sinhVienBaiDang",sinhVienBaiDangDto);
        return "student/job/apply";
    }

    @PostMapping("/sinh-vien/ung-tuyen")
    public String applyBaidang(@ModelAttribute("sinhVienBaiDang") SinhVienBaiDangDto sinhVienBaiDangDto){
        try {
            sinhVienBaiDangService.applyBaiDang(sinhVienBaiDangDto);
            return "redirect:/sinh-vien/bai-dang/"+sinhVienBaiDangDto.getMaBaiDang();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
