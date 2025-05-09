package com.graduate.recruitment.controller.admin;

import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.repository.KyNangRepository;
import com.graduate.recruitment.service.DanhMucService;
import com.graduate.recruitment.service.KyNangService;
import com.graduate.recruitment.service.NhaTruongService;
import com.graduate.recruitment.service.SinhVienService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private KyNangService kyNangService;
    private DanhMucService danhMucService;
    private NhaTruongService nhaTruongService;
    private SinhVienService sinhVienService;
    @GetMapping("/ky-nang")
    public String skill(Model model,
                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<KyNang> kyNangs = kyNangService.getAllKyNang(page,limit);
        model.addAttribute("kyNangs",kyNangs.getContent());
        return "admin/skill/list";
    }

    @GetMapping("/danh-muc")
    public String danhMuc(Model model,
                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<DanhMuc> danhMucs = danhMucService.getAllDanhMuc(page, limit);
        model.addAttribute("danhMucs",danhMucs.getContent());
        return "admin/danh-muc/list";
    }

    @GetMapping("/nha-truong")
    public String nhaTruong(Model model,
                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<NhaTruong> nhaTruongs = nhaTruongService.getAllNhaTruong(page, limit);
        model.addAttribute("nhaTruongs",nhaTruongs.getContent());
        return "admin/nha-truong/list";
    }

    @GetMapping("/sinh-vien")
    public String sinhVien(Model model,
                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<SinhVien> sinhViens = sinhVienService.getAllSinhVien(page, limit);
        model.addAttribute("sinhViens",sinhViens.getContent());
        return "admin/sinh-vien/list";
    }

    @GetMapping("/doanh-nghiep")
    public String doanhNghiep(Model model,
                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        return "admin/doanh-nghiep/list";
    }

    @GetMapping("/bai-dang")
    public String baiDang(Model model,
                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        return "admin/bai-dang/list";
    }
}
