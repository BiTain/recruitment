package com.graduate.recruitment.controller;

import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.LoiMoiThucTap;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.service.LichPhongVanService;
import com.graduate.recruitment.service.LoiMoiThucTapService;
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
    private LoiMoiThucTapService loiMoiThucTapService;
    @GetMapping("/sinh-vien/bai-dang/da-ung-tuyen")
    public String showAllBaiDang(Model model){
        model.addAttribute("sinhVienBaiDangs",sinhVienBaiDangService.getBaiDangApplied("SV001"));
        return "student/info/job";
    }

    @GetMapping("sinh-vien/lich-phong-van")
    public String showLichPhongVan(Model model,
                                   @RequestParam(value = "status", defaultValue = "sap-toi", required = false)
                                   String status){
        Map<String, List<LichPhongVan>> lichPhongVanByTrangThai =
                lichPhongVanService.getAllLichPhongVanByTrangThai("SV009");

        // Thêm dữ liệu vào model
        model.addAttribute("lichSapToi", lichPhongVanByTrangThai.get("sap-toi"));
        model.addAttribute("lichDangCho", lichPhongVanByTrangThai.get("dang-cho"));
        model.addAttribute("lichHoanThanh", lichPhongVanByTrangThai.get("hoan-thanh"));

        // Đếm số lượng phỏng vấn theo từng loại
        model.addAttribute("upcomingCount", lichPhongVanByTrangThai.get("sap-toi").size());
        model.addAttribute("waitingCount", lichPhongVanByTrangThai.get("dang-cho").size());
        model.addAttribute("completedCount", lichPhongVanByTrangThai.get("hoan-thanh").size());
        model.addAttribute("activeTab", status);
        return "student/info/interview";
    }

    @GetMapping("sinh-vien/loi-moi-thuc-tap")
    public String showLoiMoiThucTap(Model model,
                                    @RequestParam(value = "status",defaultValue = "dang-cho",required = false)
                                    String status){
        Map<String, List<LoiMoiThucTap>> lmttByTrangThai =
                loiMoiThucTapService.getAllLoiMoiThucTapByTrangThai("SV008");

        model.addAttribute("lmttDangCho",lmttByTrangThai.get("dang-cho"));
        model.addAttribute("lmttChapNhan",lmttByTrangThai.get("chap-nhan"));
        model.addAttribute("lmttTuChoi",lmttByTrangThai.get("tu-choi"));

        model.addAttribute("activeTab", status);
        return "student/info/internship";

    }

    @GetMapping("sinh-vien/tong-quan")
    public String showInfoSinhVien(Model model){
        SinhVien sinhVien = sinhVienService.getByMaTaiKhoan("TK016");
        if(sinhVien == null){
            model.addAttribute("sinhVien",new SinhVienDto());
        }else{
            model.addAttribute("sinhVien",sinhVienService.getByMaSinhVien(sinhVien.getMaSinhVien()));
        }
        return "student/info/student";
    }
}
