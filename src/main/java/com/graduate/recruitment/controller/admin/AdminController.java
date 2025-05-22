package com.graduate.recruitment.controller.admin;

import com.graduate.recruitment.dto.KyNangDto;
import com.graduate.recruitment.entity.*;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.repository.DanhMucRepository;
import com.graduate.recruitment.repository.KyNangRepository;
import com.graduate.recruitment.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private KyNangService kyNangService;
    private DanhMucService danhMucService;
    private NhaTruongService nhaTruongService;
    private SinhVienService sinhVienService;
    private BaiDangService baiDangService;
    private DanhMucRepository danhMucRepository;
    private KyNangRepository kyNangRepository;

    @GetMapping("/ky-nang")
    public String skill(Model model,
                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<KyNang> kyNangs = kyNangService.getAllKyNang(page,limit);
        model.addAttribute("kyNangs",kyNangs.getContent());
        model.addAttribute("danhMucs",danhMucRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", kyNangs.getTotalPages());
        model.addAttribute("totalItems", kyNangs.getTotalElements());
        return "admin/skill/list";
    }

    @PostMapping("/ky-nang/them")
    public String themKyNang(
            @RequestParam("tenKyNang") String tenKyNang,
            @RequestParam("maDanhMuc") String maDanhMuc) {
        KyNangDto dto = new KyNangDto();
        dto.setTenKyNang(tenKyNang);
        dto.setMaDanhMuc(maDanhMuc);
        if(kyNangRepository.findKyNangByTenKyNang(tenKyNang).isEmpty()) {
            kyNangService.themKyNang(dto);
            return "redirect:/admin/ky-nang";
        }
        return "redirect:/admin/ky-nang";
    }

    @PutMapping("/ky-nang/cap-nhap")
    public String capNhapKyNang(
            @RequestParam("tenKyNang") String tenKyName,
            @RequestParam("maDanhMuc") String maDanhMuc) {
        KyNangDto dto = new KyNangDto();
        dto.setTenKyNang(tenKyName);
        dto.setMaDanhMuc(maDanhMuc);
        kyNangService.themKyNang(dto);
        return "redirect:/admin/ky-nang";
    }

    @DeleteMapping("/ky-nang/xoa")
    public String xoaKyNang(
            @RequestParam("maKyNang") String maKyNang
    ) {

        return "redirect:/admin/ky-nang";
    }

    @GetMapping("/danh-muc")
    public String danhMuc(Model model,
                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<DanhMuc> danhMucs = danhMucService.getAllDanhMuc(page, limit);
        model.addAttribute("danhMucs",danhMucs.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", danhMucs.getTotalPages());
        model.addAttribute("totalItems", danhMucs.getTotalElements());
        return "admin/danh-muc/list";
    }

    @GetMapping("/nha-truong")
    public String nhaTruong(Model model,
                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<NhaTruong> nhaTruongs = nhaTruongService.getAllNhaTruong(page, limit);
        List<NhaTruong> nhaTruongDaKichHoat = nhaTruongs.stream()
                .filter(nhaTruong -> nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.HOAT_DONG))
                .toList();
        model.addAttribute("nhaTruongs",nhaTruongDaKichHoat);
        List<NhaTruong> nhaTruongChoKichHoat = nhaTruongs.stream()
                .filter(nhaTruong -> nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.KHONG_HOAT_DONG))
                .toList();
        model.addAttribute("nhaTruongChoKichHoat",nhaTruongChoKichHoat);
        return "admin/nha-truong/list";
    }

    @GetMapping("/sinh-vien")
    public String sinhVien(Model model,
                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<SinhVien> sinhViens = sinhVienService.getAllSinhVien(page, limit);
        model.addAttribute("sinhViens",sinhViens.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sinhViens.getTotalPages());
        model.addAttribute("totalItems", sinhViens.getTotalElements());
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
        Page<BaiDang> baiDangs = baiDangService.getAll(page, limit);
        model.addAttribute("baiDangs",baiDangs.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", baiDangs.getTotalPages());
        model.addAttribute("totalItems", baiDangs.getTotalElements());
        return "admin/bai-dang/list";
    }
}
