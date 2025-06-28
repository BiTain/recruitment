package com.graduate.recruitment.controller.admin;

import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.dto.KyNangDto;
import com.graduate.recruitment.entity.*;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.repository.*;
import com.graduate.recruitment.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
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
    private KyNangRepository kyNangRepository;

    private TaiKhoanRepository taiKhoanRepository;
    private NhaTruongRepository nhaTruongRepository;
    private DoanhNghiepRepository doanhNghiepRepository;
    private BaiDangRepository baiDangRepository;
    private EmailService emailService;

    @GetMapping("/ky-nang")
    public String skill(Model model,
                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                        @RequestParam(value="keyword", required = false) String keyword,
                        @RequestParam(value = "selectedDanhMuc", required = false) String maDanhMuc) {
        Page<KyNang> kyNangs = kyNangService.getAllKyNang(page, limit, keyword, maDanhMuc);
        model.addAttribute("kyNangs", kyNangs.getContent());
        model.addAttribute("danhMucs", danhMucRepository.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", kyNangs.getTotalPages());
        model.addAttribute("totalItems", kyNangs.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedDanhMuc", maDanhMuc);
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
                          @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                          @RequestParam(value="keyword", required = false) String keyword) {
        Page<DanhMuc> danhMucs = danhMucService.getAllDanhMuc(page, limit, keyword);
        model.addAttribute("danhMucs", danhMucs.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", danhMucs.getTotalPages());
        model.addAttribute("totalItems", danhMucs.getTotalElements());
        model.addAttribute("keyword", keyword);
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
                            @RequestParam(value = "pageActive", defaultValue = "0") int pageActive,
                            @RequestParam(value = "pageInactive", defaultValue = "0") int pageInactive,
                            @RequestParam(value = "tab", defaultValue = "daKichHoat") String tab,
                            @RequestParam(value = "limit", defaultValue = "8") int limit,
                            @RequestParam(value = "keyword", required = false) String keyword,
                            @RequestParam(value = "status", required = false) String status) {

        Pageable pageableActive = PageRequest.of(pageActive, limit);
        Pageable pageableInactive = PageRequest.of(pageInactive, limit);

        // Parse status nếu có
        TrangThaiTaiKhoan statusFilter = null;
        if (status != null && !status.isEmpty()) {
            try {
                statusFilter = TrangThaiTaiKhoan.valueOf(status);
            } catch (IllegalArgumentException ignored) {
            }
        }

        // Lọc trường đã kích hoạt
        Page<NhaTruong> nhaTruongDaKichHoat;
        if (keyword != null && !keyword.isEmpty()) {
            if (statusFilter != null) {
                nhaTruongDaKichHoat = nhaTruongRepository
                        .findByTaiKhoan_TrangThaiAndTenTruongContainingIgnoreCase(
                                statusFilter, keyword, pageableActive);
            } else {
                nhaTruongDaKichHoat = nhaTruongRepository
                        .findByTenTruongContainingIgnoreCaseAndTaiKhoan_TrangThaiIn(
                                keyword, List.of(TrangThaiTaiKhoan.HOAT_DONG, TrangThaiTaiKhoan.BI_KHOA),
                                pageableActive);
            }
        } else {
            if (statusFilter != null) {
                nhaTruongDaKichHoat = nhaTruongRepository
                        .findByTaiKhoan_TrangThai(statusFilter, pageableActive);
            } else {
                nhaTruongDaKichHoat = nhaTruongRepository
                        .findByTaiKhoan_TrangThaiIn(
                                List.of(TrangThaiTaiKhoan.HOAT_DONG, TrangThaiTaiKhoan.BI_KHOA),
                                pageableActive);
            }
        }

        // Chờ kích hoạt có tìm kiếm
        Page<NhaTruong> nhaTruongChoKichHoat;
        if (keyword != null && !keyword.isEmpty()) {
            nhaTruongChoKichHoat = nhaTruongRepository
                    .findByTenTruongContainingIgnoreCaseAndTaiKhoan_TrangThai(
                            keyword, TrangThaiTaiKhoan.KHONG_HOAT_DONG, pageableInactive);
        } else {
            nhaTruongChoKichHoat = nhaTruongRepository
                    .findByTaiKhoan_TrangThai(TrangThaiTaiKhoan.KHONG_HOAT_DONG, pageableInactive);
        }

        model.addAttribute("nhaTruongs", nhaTruongDaKichHoat);
        model.addAttribute("nhaTruongChoKichHoat", nhaTruongChoKichHoat);
        model.addAttribute("tab", tab);
        model.addAttribute("pageActive", pageActive);
        model.addAttribute("pageInactive", pageInactive);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);

        return "admin/nha-truong/list";
    }

    @PostMapping("/nha-truong/xac-nhan")
    public String xacNhanNhaTruong(RedirectAttributes redirectAttributes,
                                   @RequestParam("maNhaTruong") String maNhaTruong,
                                   @RequestParam("trangThai") String trangThai) {
        try {
            NhaTruong nhaTruong = nhaTruongService.getNhaTruong(maNhaTruong);
            TaiKhoan taiKhoan = nhaTruong.getTaiKhoan();
            if (trangThai.equals("kich-hoat")) {
                taiKhoan.setTrangThai(TrangThaiTaiKhoan.HOAT_DONG);
                taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
                nhaTruong.setCapNhatVaoLuc(LocalDateTime.now());
                taiKhoanRepository.save(taiKhoan);
                nhaTruongRepository.save(nhaTruong);
                emailService.sendEmailByAdmin(
                        taiKhoan.getEmail(),
                        "Thông báo kích hoạt tài khoản",
                        "Tài khỏan của bạn đã được kích hoạt thành công",
                        nhaTruong.getTenTruong(),
                        "admin@admin.com");
                redirectAttributes.addFlashAttribute("successMsg", "Đã xác nhận nhà trường thành công!");
            } else if (trangThai.equals("tu-choi")) {
                nhaTruongRepository.delete(nhaTruong);
                emailService.sendEmailByAdmin(
                        taiKhoan.getEmail(),
                        "Thông báo kích hoạt tài khoản",
                        "Tài khỏan của bạn đã bị từ chối hãy liên hệ qua \uD83D\uDCDE (84-236) 3822041 hoặc \uD83D\uDCE7 vanphong.dhdn@ac.udn.vn",
                        nhaTruong.getTenTruong(),
                        "admin@admin.com");
                redirectAttributes.addFlashAttribute("successMsg", "Tài khoản nhà trường đã bị từ chối!");
            }
            return "redirect:/admin/nha-truong";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admin/nha-truong";
        }
    }

    @GetMapping("/sinh-vien")
    public String sinhVien(Model model,
                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                           @RequestParam(value = "maSchool", required = false) String maSchool,
                           @RequestParam(value = "status", required = false) String status,
                           @RequestParam(value = "keyword", required = false) String keyword) {
        Page<SinhVien> sinhViens = sinhVienService.getAllSinhVien(page, limit, status, maSchool, keyword);
        List<NhaTruong> nhaTruongs = nhaTruongRepository.findAllByTaiKhoan_TrangThaiIn(
                Arrays.asList(TrangThaiTaiKhoan.HOAT_DONG, TrangThaiTaiKhoan.BI_KHOA)
        );
        model.addAttribute("sinhViens", sinhViens.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("nhaTruongs", nhaTruongs);
        model.addAttribute("totalPages", sinhViens.getTotalPages());
        model.addAttribute("totalItems", sinhViens.getTotalElements());
        model.addAttribute("maSchool", maSchool);
        model.addAttribute("status", status);
        model.addAttribute("keyword", keyword);
        return "admin/sinh-vien/list";
    }

    @GetMapping("/doanh-nghiep")
    public String doanhNghiep(Model model,
                              @RequestParam(value = "tab", defaultValue = "daKichHoat") String tab,
                              @RequestParam(value = "pageActive", defaultValue = "0") int pageActive,
                              @RequestParam(value = "pageInactive", defaultValue = "0") int pageInactive,
                              @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "status", required = false) String status) {

        Pageable pageableActive = PageRequest.of(pageActive, limit);
        Pageable pageableInactive = PageRequest.of(pageInactive, limit);

        List<DoanhNghiep> allDoanhNghieps = doanhNghiepRepository.findAll();

        Comparator<DoanhNghiep> sortByUpdatedAtDesc = Comparator.comparing(DoanhNghiep::getCapNhatVaoLuc).reversed();

        // Lọc theo trạng thái
        List<DoanhNghiep> dsActive = allDoanhNghieps.stream()
                .filter(dn -> {
                    boolean isInActiveTab = dn.getTaiKhoan().getTrangThai() == TrangThaiTaiKhoan.HOAT_DONG
                            || dn.getTaiKhoan().getTrangThai() == TrangThaiTaiKhoan.BI_KHOA;
                    if (status != null && !status.isEmpty()) {
                        return isInActiveTab && dn.getTaiKhoan().getTrangThai().name().equals(status);
                    }
                    return isInActiveTab;
                })
                .filter(dn -> keyword == null || keyword.isEmpty()
                        || dn.getTenDoanhNghiep().toLowerCase().contains(keyword.toLowerCase()))
                .sorted(sortByUpdatedAtDesc)
                .toList();

        List<DoanhNghiep> dsInactive = allDoanhNghieps.stream()
                .filter(dn -> dn.getTaiKhoan().getTrangThai() == TrangThaiTaiKhoan.KHONG_HOAT_DONG)
                .filter(dn -> keyword == null || keyword.isEmpty()
                        || dn.getTenDoanhNghiep().toLowerCase().contains(keyword.toLowerCase()))
                .sorted(sortByUpdatedAtDesc)
                .toList();

        // Tạo trang thủ công
        Page<DoanhNghiep> pageDoanhNghiepDaKichHoat = toPage(dsActive, pageableActive);
        Page<DoanhNghiep> pageDoanhNghiepChoKichHoat = toPage(dsInactive, pageableInactive);

        // Truyền dữ liệu sang view
        model.addAttribute("doanhNghiepDaKichHoat", pageDoanhNghiepDaKichHoat);
        model.addAttribute("doanhNghiepChoKichHoat", pageDoanhNghiepChoKichHoat);
        model.addAttribute("pageActive", pageActive);
        model.addAttribute("pageInactive", pageInactive);
        model.addAttribute("limit", limit);
        model.addAttribute("tab", tab);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);

        return "admin/doanh-nghiep/list";
    }

    private Page<DoanhNghiep> toPage(List<DoanhNghiep> list, Pageable pageable) {
        int total = list.size();
        int start = Math.min((int) pageable.getOffset(), total);
        int end = Math.min(start + pageable.getPageSize(), total);
        List<DoanhNghiep> sublist = list.subList(start, end);
        return new PageImpl<>(sublist, pageable, total);
    }

    @PostMapping("/doanh-nghiep/xac-nhan")
    public String xacNhanDoanhNghiep(RedirectAttributes redirectAttributes,
                                     @RequestParam("maDoanhNghiep") String maDoanhNghiep,
                                     @RequestParam("trangThai") String trangThai) {
        try {
            DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(maDoanhNghiep)
                    .orElseThrow(() -> new EntityNotFoundException("Doanh nghiệp không tồn tại"));
            TaiKhoan taiKhoan = doanhNghiep.getTaiKhoan();
            if (trangThai.equals("kich-hoat")) {
                taiKhoan.setTrangThai(TrangThaiTaiKhoan.HOAT_DONG);
                taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
                doanhNghiep.setCapNhatVaoLuc(LocalDateTime.now());
                taiKhoanRepository.save(taiKhoan);
                doanhNghiepRepository.save(doanhNghiep);
                emailService.sendEmailByAdmin(
                        taiKhoan.getEmail(),
                        "Thông báo kích hoạt tài khoản",
                        "Tài khỏan của bạn đã được kích hoạt thành công",
                        doanhNghiep.getTenDoanhNghiep(),
                        "admin@admin.com");
                redirectAttributes.addFlashAttribute("successMsg", "Đã xác nhận doanh nghiệp thành công!");
            } else if (trangThai.equals("tu-choi")) {
                doanhNghiepRepository.delete(doanhNghiep);
                emailService.sendEmailByAdmin(
                        taiKhoan.getEmail(),
                        "Thông báo kích hoạt tài khoản",
                        "Tài khỏan của bạn đã bị từ chối hãy liên hệ qua \uD83D\uDCDE (84-236) 3822041 hoặc \uD83D\uDCE7 vanphong.dhdn@ac.udn.vn",
                        doanhNghiep.getTenDoanhNghiep(),
                        "admin@admin.com");
                redirectAttributes.addFlashAttribute("successMsg", "Tài khoản doanh nghiệp đã bị từ chối!");
            }
            return "redirect:/admin/doanh-nghiep";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admin/doanh-nghiep";
        }
    }

    @GetMapping("/bai-dang")
    public String baiDang(Model model,
                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                          @RequestParam(value = "keyword", required = false,defaultValue = "") String keyword,
                          @RequestParam(value = "doanhNghiep", required = false,defaultValue = "") String maDoanhNghiep,
                          @RequestParam(value = "trangThai", required = false, defaultValue = "") String trangThai,
                          @RequestParam(value = "loai", required = false, defaultValue = "") String loai) {
        Page<BaiDang> baiDangs = baiDangService.getAll(page, limit, keyword, maDoanhNghiep, trangThai, loai);
        List<DoanhNghiep> doanhNghieps = doanhNghiepRepository.findAll().stream()
                .filter(doanhNghiep -> doanhNghiep.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.HOAT_DONG)
                || doanhNghiep.getTaiKhoan().getTrangThai().equals(TrangThaiTaiKhoan.BI_KHOA))
                .toList();

        model.addAttribute("doanhNghieps",doanhNghieps);
        model.addAttribute("baiDangs", baiDangs.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", baiDangs.getTotalPages());
        model.addAttribute("totalItems", baiDangs.getTotalElements());
        model.addAttribute("selectedDoanhNghiep", maDoanhNghiep);
        model.addAttribute("selectedTrangThai", trangThai);
        model.addAttribute("keyword",keyword);
        model.addAttribute("selectedLoai", loai);
        return "admin/bai-dang/list";
    }

    @PostMapping("/bai-dang/khoa-bai-dang")
    public String khoaBaiDang(RedirectAttributes redirectAttributes,
                              @RequestParam String maBaiDang) {
        try {
            BaiDang baiDang = baiDangService.getByMaBaiDang1(maBaiDang);
            if (baiDang != null) {
                baiDang.setTrangThai(TrangThaiBaiDang.KHOA_BOI_ADMIN);
                baiDang.setCapNhatVaoLuc(LocalDateTime.now());
                baiDangRepository.save(baiDang);
                redirectAttributes.addFlashAttribute("successMsg", "Đã khóa bài đăng!");
            }
            return "redirect:/admin/bai-dang";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admin/bai-dang";
        }
    }

    @PostMapping("/bai-dang/mo-khoa")
    public String moKhoaBaiDang(RedirectAttributes redirectAttributes,
                                @RequestParam String maBaiDang) {
        try {
            BaiDang baiDang = baiDangService.getByMaBaiDang1(maBaiDang);
            if (baiDang.getDenHan().isAfter(LocalDateTime.now())) {
                baiDang.setTrangThai(TrangThaiBaiDang.CON_HAN);
            } else {
                baiDang.setTrangThai(TrangThaiBaiDang.HET_HAN);
            }
            baiDang.setCapNhatVaoLuc(LocalDateTime.now());
            baiDangRepository.save(baiDang);
            redirectAttributes.addFlashAttribute("successMsg", "Bài đăng đã được mở khóa!");
            return "redirect:/admin/bai-dang";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admin/bai-dang";
        }
    }

    @PostMapping("/tai-khoan")
    public String quanLyTaiKhoan(RedirectAttributes redirectAttributes,
                                 @RequestParam String maTaiKhoan,
                                 @RequestParam String trangThai,
                                 @RequestParam String trang) {
        try {
            TaiKhoan taiKhoan = taiKhoanRepository.findById(maTaiKhoan)
                    .orElseThrow(() -> new EntityNotFoundException("Tài khoản không tồn tại"));
            if (trangThai.equals("khoa")) {
                taiKhoan.setTrangThai(TrangThaiTaiKhoan.BI_KHOA);
                emailService.sendEmailByAdmin(
                        taiKhoan.getEmail(),
                        "Thông báo tài khoản bị khóa",
                        "Tài khỏan của bạn đã khóa hãy liên hệ qua \uD83D\uDCDE (84-236) 3822041 hoặc \uD83D\uDCE7 vanphong.dhdn@ac.udn.vn",
                        taiKhoan.getEmail(),
                        "admin@admin.com");
                redirectAttributes.addFlashAttribute("successMsg", "Tài khoản đã bị khoá!");
            } else if (trangThai.equals("mo")) {
                taiKhoan.setTrangThai(TrangThaiTaiKhoan.HOAT_DONG);
                redirectAttributes.addFlashAttribute("successMsg", "Tài khoản được mở khoá!");
            }
            taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
            taiKhoanRepository.save(taiKhoan);

            String url = null;
            if (trang.equals("doanh-nghiep")) {
                url = "redirect:/admin/doanh-nghiep";
            } else if (trang.equals("nha-truong")) {
                url = "redirect:/admin/nha-truong";
            } else if (trang.equals("sinh-vien")) {
                url = "redirect:/admin/sinh-vien";
            }
            return url;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            String url = null;
            if (trang.equals("doanh-nghiep")) {
                url = "redirect:/admin/doanh-nghiep";
            } else if (trang.equals("nha-truong")) {
                url = "redirect:/admin/nha-truong";
            } else if (trang.equals("sinh-vien")) {
                url = "redirect:/admin/sinh-vien";
            }
            return url;
        }
    }

    @GetMapping("/dang-xuat")
    public String dangXuat(HttpServletRequest request, HttpServletResponse response) {
        // ✅ Xóa thông tin xác thực
        SecurityContextHolder.clearContext();

        // ✅ Xóa session
        HttpSession session = request.getSession(false); // false để không tạo session mới
        if (session != null) {
            session.invalidate(); // huỷ session hiện tại
        }

        return "redirect:/"; // hoặc trang bạn muốn đưa người dùng về sau khi đăng xuất
    }
}
