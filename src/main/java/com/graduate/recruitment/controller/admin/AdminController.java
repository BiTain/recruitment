package com.graduate.recruitment.controller.admin;

import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.dto.KyNangDto;
import com.graduate.recruitment.entity.*;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.repository.*;
import com.graduate.recruitment.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private TaiKhoanRepository taiKhoanRepository;
    private NhaTruongRepository nhaTruongRepository;
    private DoanhNghiepRepository doanhNghiepRepository;
    private BaiDangRepository baiDangRepository;
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
            @RequestParam("tenKyNang") String tenKyName,
            @RequestParam("maDanhMuc") String maDanhMuc) {
        KyNangDto dto = new KyNangDto();
        dto.setTenKyNang(tenKyName);
        dto.setMaDanhMuc(maDanhMuc);
        kyNangService.themKyNang(dto);
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
        List<NhaTruong> nhaTruongs = nhaTruongRepository.findAll();
        List<NhaTruong> nhaTruongDaKichHoat = nhaTruongs.stream()
                .filter(nhaTruong -> nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.HOAT_DONG)
                || nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.BI_KHOA))
                        .toList();
        model.addAttribute("nhaTruongs",nhaTruongDaKichHoat);
        List<NhaTruong> nhaTruongChoKichHoat = nhaTruongs.stream()
                .filter(nhaTruong -> nhaTruong.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.KHONG_HOAT_DONG))
                .toList();
        model.addAttribute("nhaTruongChoKichHoat",nhaTruongChoKichHoat);
        return "admin/nha-truong/list";
    }

    @PostMapping("/nha-truong/xac-nhan")
    public String xacNhanNhaTruong(RedirectAttributes redirectAttributes,
                                   @RequestParam("maNhaTruong") String maNhaTruong,
                                   @RequestParam("trangThai") String trangThai){
        try {
            NhaTruong nhaTruong = nhaTruongService.getNhaTruong(maNhaTruong);
            TaiKhoan taiKhoan = nhaTruong.getTaiKhoan();
            if (trangThai.equals("kich-hoat")){
                taiKhoan.setTrangThai(TrangThaiTaiKhoan.HOAT_DONG);
                taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
                nhaTruong.setCapNhatVaoLuc(LocalDateTime.now());
                taiKhoanRepository.save(taiKhoan);
                nhaTruongRepository.save(nhaTruong);
                redirectAttributes.addFlashAttribute("successMsg","Đã xác nhận nhà trường thành công!");
            } else if (trangThai.equals("tu-choi")) {
                nhaTruongRepository.delete(nhaTruong);
                redirectAttributes.addFlashAttribute("successMsg","Tài khoản nhà trường đã bị từ chối!");
            }
            return "redirect:/admin/nha-truong";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/admin/nha-truong";
        }
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
        List<DoanhNghiep> doanhNghieps = doanhNghiepRepository.findAll();
        List<DoanhNghiep> doanhNghiepDaKichHoat = doanhNghieps.stream()
                .filter(doanhNghiep -> doanhNghiep.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.HOAT_DONG)
                || doanhNghiep.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.BI_KHOA))
                .toList();
        model.addAttribute("doanhNghiepDaKichHoat",doanhNghiepDaKichHoat);
        List<DoanhNghiep> doanhNghiepChoKichHoat = doanhNghieps.stream()
                .filter(doanhNghiep -> doanhNghiep.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.KHONG_HOAT_DONG))
                .toList();
        model.addAttribute("doanhNghiepChoKichHoat",doanhNghiepChoKichHoat);
        return "admin/doanh-nghiep/list";
    }

    @PostMapping("/doanh-nghiep/xac-nhan")
    public String xacNhanDoanhNghiep(RedirectAttributes redirectAttributes,
                                   @RequestParam("maDoanhNghiep") String maDoanhNghiep,
                                   @RequestParam("trangThai") String trangThai){
        try {
            DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(maDoanhNghiep)
                    .orElseThrow(()-> new EntityNotFoundException("Doanh nghiệp không tồn tại"));
            TaiKhoan taiKhoan = doanhNghiep.getTaiKhoan();
            if (trangThai.equals("kich-hoat")){
                taiKhoan.setTrangThai(TrangThaiTaiKhoan.HOAT_DONG);
                taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
                doanhNghiep.setCapNhatVaoLuc(LocalDateTime.now());
                taiKhoanRepository.save(taiKhoan);
                doanhNghiepRepository.save(doanhNghiep);
                redirectAttributes.addFlashAttribute("successMsg","Đã xác nhận doanh nghiệp thành công!");
            } else if (trangThai.equals("tu-choi")) {
                doanhNghiepRepository.delete(doanhNghiep);
                redirectAttributes.addFlashAttribute("successMsg","Tài khoản doanh nghiệp đã bị từ chối!");
            }
            return "redirect:/admin/doanh-nghiep";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/admin/doanh-nghiep";
        }
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

    @PostMapping("/bai-dang/khoa-bai-dang")
    public String khoaBaiDang(RedirectAttributes redirectAttributes,
                              @RequestParam String maBaiDang){
        try {
            BaiDang baiDang = baiDangService.getByMaBaiDang1(maBaiDang);
            if(baiDang!=null){
                baiDang.setTrangThai(TrangThaiBaiDang.KHOA_BOI_ADMIN);
                baiDang.setCapNhatVaoLuc(LocalDateTime.now());
                baiDangRepository.save(baiDang);
                redirectAttributes.addFlashAttribute("successMsg","Đã khóa bài đăng!");
            }
            return "redirect:/admin/bai-dang";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/admin/bai-dang";
        }
    }

    @PostMapping("/bai-dang/mo-khoa")
    public String moKhoaBaiDang(RedirectAttributes redirectAttributes,
                                @RequestParam String maBaiDang){
        try {
            BaiDang baiDang = baiDangService.getByMaBaiDang1(maBaiDang);
            if (baiDang.getDenHan().isAfter(LocalDateTime.now())){
                baiDang.setTrangThai(TrangThaiBaiDang.CON_HAN);
            }else{
                baiDang.setTrangThai(TrangThaiBaiDang.HET_HAN);
            }
            baiDang.setCapNhatVaoLuc(LocalDateTime.now());
            baiDangRepository.save(baiDang);
            redirectAttributes.addFlashAttribute("successMsg","Bài đăng đã được mở khóa!");
            return "redirect:/admin/bai-dang";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/admin/bai-dang";
        }
    }

    @PostMapping("/tai-khoan")
    public String quanLyTaiKhoan(RedirectAttributes redirectAttributes,
                               @RequestParam String maTaiKhoan,
                                 @RequestParam String trangThai,
                                 @RequestParam String trang){
        try {
            TaiKhoan taiKhoan = taiKhoanRepository.findById(maTaiKhoan)
                    .orElseThrow(()-> new EntityNotFoundException("Tài khoản không tồn tại"));
            if(trangThai.equals("khoa")){
                taiKhoan.setTrangThai(TrangThaiTaiKhoan.BI_KHOA);
                redirectAttributes.addFlashAttribute("successMsg","Tài khoản đã bị khoá!");
            }else if (trangThai.equals("mo")){
                taiKhoan.setTrangThai(TrangThaiTaiKhoan.HOAT_DONG);
                redirectAttributes.addFlashAttribute("successMsg","Tài khoản được mở khoá!");
            }
            taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
            taiKhoanRepository.save(taiKhoan);

            String url = null;
            if (trang.equals("doanh-nghiep")){
                url = "redirect:/admin/doanh-nghiep";
            }else if (trang.equals("nha-truong")){
                url = "redirect:/admin/nha-truong";
            }else if (trang.equals("sinh-vien")){
                url = "redirect:/admin/sinh-vien";
            }
            return url;
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            String url = null;
            if (trang.equals("doanh-nghiep")){
                url = "redirect:/admin/doanh-nghiep";
            }else if (trang.equals("nha-truong")){
                url = "redirect:/admin/nha-truong";
            }else if (trang.equals("sinh-vien")){
                url = "redirect:/admin/sinh-vien";
            }
            return url;
        }
    }
}
