package com.graduate.recruitment.controller;

import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.service.LichPhongVanService;
import com.graduate.recruitment.service.SinhVienBaiDangService;
import com.graduate.recruitment.service.SinhVienService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ThongTinController {

    private SinhVienBaiDangService sinhVienBaiDangService;
    private LichPhongVanService lichPhongVanService;
    private SinhVienService sinhVienService;
    @GetMapping("/sinh-vien/bai-dang/da-ung-tuyen")
    public String showAllBaiDang(Model model){
        model.addAttribute("sinhVienBaiDangs",sinhVienBaiDangService.getBaiDangApplied("SV001"));
        return "student/info/job";
    }

    @GetMapping("sinh-vien/lich-phong-van")
    public String showLichPhongVan(Model model,
                                   @RequestParam(value = "status", defaultValue = "sap-toi", required = false)
                                   String status){
        Map<String, List<LichPhongVan>> interviewsByStatus =
                lichPhongVanService.getAllLichPhongVanByTrangThai("SV009");

        // Thêm dữ liệu vào model
        model.addAttribute("lichSapToi", interviewsByStatus.get("sap-toi"));
        model.addAttribute("lichDangCho", interviewsByStatus.get("dang-cho"));
        model.addAttribute("lichHoanThanh", interviewsByStatus.get("hoan-thanh"));

        // Đếm số lượng phỏng vấn theo từng loại
        model.addAttribute("upcomingCount", interviewsByStatus.get("sap-toi").size());
        model.addAttribute("waitingCount", interviewsByStatus.get("dang-cho").size());
        model.addAttribute("completedCount", interviewsByStatus.get("hoan-thanh").size());
        model.addAttribute("activeTab", status);
        return "student/info/interview";
    }

    @GetMapping("sinh-vien/tong-quan")
    public String showInfoSinhVien(Model model){
        model.addAttribute("sinhVien",sinhVienService.getByMaSinhVien("SV001"));
        return "student/info/student";
    }
}
