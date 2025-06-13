package com.graduate.recruitment.controller;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.LichPhongVanDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
import com.graduate.recruitment.repository.BaiDangRepository;
import com.graduate.recruitment.repository.DanhMucRepository;
import com.graduate.recruitment.repository.KyNangRepository;
import com.graduate.recruitment.service.BaiDangService;
import com.graduate.recruitment.service.SinhVienBaiDangService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class BaiDangController {
    private BaiDangService baiDangService;
    private DanhMucRepository danhMucRepository;
    private KyNangRepository kyNangRepository;
    private BaiDangRepository baiDangRepository;

    @GetMapping("/sinh-vien/bai-dang")
    public String showAllBaiDang(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                                 @RequestParam(value = "search", required = false) String tuKhoa,
                                 @RequestParam(value = "kyNang", required = false) String kyNang,
                                 @RequestParam(value = "selectedJob", required = false) String maBaiDang,
                                 @RequestParam(value = "hinhThucLamViec", required = false) String hinhThucLamViec,
                                 @RequestParam(value= "khuVuc", required = false) String khuVuc,
                                 Model model) {
        Page<BaiDangDto> baiDangPage = baiDangService.getAllSinhVienBaiDang(page, limit, kyNang, tuKhoa, hinhThucLamViec, khuVuc);
        List<BaiDangDto> baiDangs = baiDangPage.getContent();
        List<KyNang> kyNangs = kyNangRepository.findAll();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", baiDangPage.getTotalPages());
        model.addAttribute("totalItems", baiDangPage.getTotalElements());
        model.addAttribute("baiDangs", baiDangs);
        model.addAttribute("kyNangs", kyNangs);
        if (maBaiDang == null && !baiDangs.isEmpty()) {
            maBaiDang = baiDangs.get(0).getMaBaiDang();
        }
        if (maBaiDang != null) {
            BaiDangDto baiDangSelected = baiDangService.getByMaBaiDang(maBaiDang);
            model.addAttribute("selectedJob", baiDangSelected);
            model.addAttribute("maBaiDang", maBaiDang);
        }
        model.addAttribute("maKyNang", kyNang);
        model.addAttribute("search", tuKhoa);
        model.addAttribute("khuVuc", khuVuc);
        model.addAttribute("hinhThucLamViec", hinhThucLamViec);

        return "student/job/list";
    }

    @GetMapping("/sinh-vien/bai-dang/{id}")
    public String showBaiDang(@PathVariable String id,
                              Model model) {
        model.addAttribute("baiDang", baiDangService.getByMaBaiDang1(id));
        return "student/job/detail";
    }

    @GetMapping("/doanh-nghiep/bai-dang")
    public String ushowAllBaiDang(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                                  @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                  @RequestParam(value = "danhMuc", required = false, defaultValue = "") String maDanhMuc,
                                  @RequestParam(value = "trangThai", required = false, defaultValue = "") String trangThai,
                                  @RequestParam(value = "loai", required = false, defaultValue = "") String loai,
                                  Model model) {
        Page<BaiDang> baiDangsPage = baiDangService.getAllBaiDangByMaDoanhNghiep(page, limit, "DN001", keyword, maDanhMuc, trangThai, loai);
        model.addAttribute("danhMucs", danhMucRepository.findAll());
        model.addAttribute("baiDangs", baiDangsPage);
        model.addAttribute("kyNangs", kyNangRepository.findAll());
        model.addAttribute("totalPages", baiDangsPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("limit", limit);
        model.addAttribute("selectedDanhMuc", maDanhMuc);
        model.addAttribute("selectedTrangThai", trangThai);
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedLoai", loai);
        return "business/job/list";
    }

    @PostMapping("/doanh-nghiep/bai-dang/tao")
    public String taoBaiDang(RedirectAttributes redirectAttributes,
                             @ModelAttribute BaiDangDto baiDangDto) {
        baiDangDto.setMaDoanhNghiep("DN001");
        try {
            BaiDang baiDang = baiDangService.taoBaiDang(baiDangDto);
            if (baiDang != null) {
                redirectAttributes.addFlashAttribute("successMsg", "Tạo bài đăng thành công");
            } else {
                redirectAttributes.addFlashAttribute("errorMsg", "Tạo bài đăng thất bại");
            }
            return "redirect:/doanh-nghiep/bai-dang";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMsg", "Tạo bài đăng thất bại");
            return "redirect:/doanh-nghiep/bai-dang";
        }
    }

    @PostMapping("/doanh-nghiep/bai-dang/chinh-sua")
    public String updateBaiDang(RedirectAttributes redirectAttributes,
                                @ModelAttribute BaiDangDto baiDangDto) {
        baiDangDto.setMaDoanhNghiep("DN001");
        try {
            BaiDang baiDang = baiDangService.updateBaiDang(baiDangDto);
            if (baiDang != null) {
                redirectAttributes.addFlashAttribute("successMsg", "Cập nhật bài đăng thành công");
            } else {
                redirectAttributes.addFlashAttribute("errorMsg", "Cập nhật bài đăng thất bại");
            }
            return "redirect:/doanh-nghiep/bai-dang";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMsg", "Cập nhật bài đăng thất bại");
            return "redirect:/doanh-nghiep/bai-dang";
        }
    }

    @PostMapping("/doanh-nghiep/bai-dang/khoa-bai-dang")
    public String khoaBaidang(RedirectAttributes redirectAttributes,
                              @RequestParam String maBaiDang) {
        try {
            BaiDang baiDang = baiDangService.getByMaBaiDang1(maBaiDang);
            if (baiDang != null && baiDang.getTrangThai().equals(TrangThaiBaiDang.CON_HAN)) {
                baiDang.setTrangThai(TrangThaiBaiDang.KHOA_BOI_DN);
                baiDang.setCapNhatVaoLuc(LocalDateTime.now());
                baiDangRepository.save(baiDang);
                redirectAttributes.addFlashAttribute("successMsg", "Bài đăng đã được khóa!");
            }
            return "redirect:/doanh-nghiep/bai-dang";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/doanh-nghiep/bai-dang";
        }
    }

    @PostMapping("/doanh-nghiep/bai-dang/mo-khoa")
    public String moKhoaBaiDang(RedirectAttributes redirectAttributes,
                                @RequestParam String maBaiDang) {
        try {
            BaiDang baiDang = baiDangService.getByMaBaiDang1(maBaiDang);
            if (baiDang.getDenHan().isAfter(LocalDateTime.now())) {
                baiDang.setTrangThai(TrangThaiBaiDang.CON_HAN);
                redirectAttributes.addFlashAttribute("successMsg", "Bài đăng đã mở chạy!");
            } else {
                baiDang.setTrangThai(TrangThaiBaiDang.HET_HAN);
                redirectAttributes.addFlashAttribute("warningMsg", "Bài đăng đã hết hạn!");
            }
            baiDang.setCapNhatVaoLuc(LocalDateTime.now());
            baiDangRepository.save(baiDang);
            return "redirect:/doanh-nghiep/bai-dang";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/doanh-nghiep/bai-dang";
        }
    }

}
