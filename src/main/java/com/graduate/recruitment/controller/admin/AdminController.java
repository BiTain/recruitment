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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
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
        Page<KyNang> kyNangs = kyNangService.getAllKyNang(page, limit);
        model.addAttribute("kyNangs", kyNangs.getContent());
        model.addAttribute("danhMucs", danhMucRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", kyNangs.getTotalPages());
        model.addAttribute("totalItems", kyNangs.getTotalElements());
        return "admin/skill/list";
    }

    @PostMapping("/ky-nang/them")
    public String themKyNang(
            @RequestParam("tenKyNang") String tenKyNang,
            @RequestParam("maDanhMuc") String maDanhMuc, RedirectAttributes redirectAttributes) {
        if (kyNangRepository.findKyNangByTenKyNang(tenKyNang).isEmpty()) {

            try {
                KyNangDto dto = new KyNangDto();
                dto.setTenKyNang(tenKyNang);
                dto.setMaDanhMuc(maDanhMuc);
                kyNangService.themKyNang(dto);

                redirectAttributes.addFlashAttribute("successMessage", "Thêm kỹ năng thành công!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Thêm kỹ năng thất bại!");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên kỹ năng đã tồn tại.");
            return "redirect:/admin/ky-nang";
        }

        return "redirect:/admin/ky-nang";
    }

    @PostMapping("/ky-nang/cap-nhat")
    public String capNhatKyNang(
            @RequestParam("maKyNang") String maKyNang,
            @RequestParam("tenKyNang") String tenKyNang,
            @RequestParam("maDanhMuc") String maDanhMuc,
            RedirectAttributes redirectAttributes) {

        boolean daTrungTen = kyNangRepository
                .findByTenKyNangAndMaKyNangNot(tenKyNang, maKyNang)
                .isPresent();

        if (daTrungTen) {
            redirectAttributes.addFlashAttribute("error", "Tên kỹ năng đã tồn tại.");
            return "redirect:/admin/ky-nang";
        }

        KyNangDto dto = new KyNangDto();
        dto.setMaKyNang(maKyNang);
        dto.setTenKyNang(tenKyNang);
        dto.setMaDanhMuc(maDanhMuc);

        kyNangService.suaKyNang(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật kỹ năng thành công!");
        return "redirect:/admin/ky-nang";
    }


    @PostMapping("/ky-nang/xoa")
    public String xoaKyNang(@RequestParam("maKyNang") String maKyNang,
                            RedirectAttributes redirectAttributes) {
        try {
            kyNangService.xoaKyNang(maKyNang);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa kỹ năng thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Xóa kỹ năng thất bại!");
        }
        return "redirect:/admin/ky-nang";
    }


    @GetMapping("/danh-muc")
    public String danhMuc(Model model,
                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<DanhMuc> danhMucs = danhMucService.getAllDanhMuc(page, limit);
        model.addAttribute("danhMucs", danhMucs.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", danhMucs.getTotalPages());
        model.addAttribute("totalItems", danhMucs.getTotalElements());
        return "admin/danh-muc/list";
    }

    @PostMapping("/danh-muc/them")
    public String themDanhMuc(
            @RequestParam("tenDanhMuc") String tenDanhMuc,
            RedirectAttributes redirectAttributes) {
        if (danhMucRepository.findByTenDanhMuc(tenDanhMuc).isEmpty()) {
            try {
                DanhMuc dm = new DanhMuc();
                long size = danhMucRepository.count();
                dm.setMaDanhMuc(String.format("DM%03d", size + 1));
                dm.setTenDanhMuc(tenDanhMuc);
                dm.setTaoVaoLuc(LocalDateTime.now());
                danhMucRepository.save(dm);

                redirectAttributes.addFlashAttribute("successMessage", "Thêm danh mục thành công!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Thêm danh mục thất bại!");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên danh mục đã tồn tại.");
            return "redirect:/admin/danh-muc";
        }

        return "redirect:/admin/danh-muc";
    }

    @PostMapping("/danh-muc/cap-nhat")
    public String capNhapDanhMuc(
            @RequestParam("maDanhMuc") String maDanhMuc,
            @RequestParam("tenDanhMuc") String tenDanhMuc,
            RedirectAttributes redirectAttributes) {

        boolean daTrungTen = danhMucRepository
                .findByTenDanhMucAndMaDanhMucNot(tenDanhMuc, maDanhMuc)
                .isPresent();

        if (daTrungTen) {
            redirectAttributes.addFlashAttribute("error", "Tên danh mục đã tồn tại.");
            return "redirect:/admin/danh-muc";
        }

        DanhMuc dm = danhMucRepository.findById(maDanhMuc).get();
        dm.setTenDanhMuc(tenDanhMuc);

        danhMucRepository.save(dm);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật danh mục thành công!");
        return "redirect:/admin/danh-muc";
    }

    @GetMapping("/nha-truong")
    public String nhaTruong(Model model,
                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<NhaTruong> nhaTruongs = nhaTruongService.getAllNhaTruong(page, limit);
        List<NhaTruong> nhaTruongDaKichHoat = nhaTruongs.stream()
                .filter(nhaTruong -> nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.HOAT_DONG))
                .toList();
        model.addAttribute("nhaTruongs", nhaTruongDaKichHoat);
        List<NhaTruong> nhaTruongChoKichHoat = nhaTruongs.stream()
                .filter(nhaTruong -> nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.KHONG_HOAT_DONG))
                .toList();
        model.addAttribute("nhaTruongChoKichHoat", nhaTruongChoKichHoat);
        return "admin/nha-truong/list";
    }

//    @PostMapping("/nha-truong/xac-nhan")
//    public String xacNhanNhaTruong(RedirectAttributes redirectAttributes,
//                                   @RequestParam("maNhaTruong") String maNhaTruong,
//                                   @RequestParam("trangThai") String trangThai){
//        NhaTruong nhaTruong =
//    }

    @GetMapping("/sinh-vien")
    public String sinhVien(Model model,
                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        Page<SinhVien> sinhViens = sinhVienService.getAllSinhVien(page, limit);
        model.addAttribute("sinhViens", sinhViens.getContent());
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
        model.addAttribute("baiDangs", baiDangs.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", baiDangs.getTotalPages());
        model.addAttribute("totalItems", baiDangs.getTotalElements());
        return "admin/bai-dang/list";
    }
}
