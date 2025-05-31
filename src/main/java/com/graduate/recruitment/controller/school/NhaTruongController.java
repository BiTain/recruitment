package com.graduate.recruitment.controller.school;

import com.graduate.recruitment.dto.NhaTruongDto;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.TrangThaiSinhVien;
import com.graduate.recruitment.mapper.NhaTruongMapper;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.service.FileService;
import com.graduate.recruitment.service.NhaTruongService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class NhaTruongController {
    private NhaTruongService nhaTruongService;
    private NhaTruongRepository nhaTruongRepository;
    private FileService fileService;
    private SinhVienRepository sinhVienRepository;

    @GetMapping("nha-truong/thong-tin")
    public String getNhaTruong(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int limit) {
        NhaTruong nt = nhaTruongService.getNhaTruong("NT001");
        model.addAttribute("nhaTruong", nt);
        model.addAttribute("nhaTruongDto", NhaTruongMapper.toDto(nt));
        return "school/thong-tin-truong";
    }

    @GetMapping("nha-truong/quan-ly-sinh-vien")
    public String getSinhVienOfNhaTruong(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "8") int limit,
                                         @RequestParam(value = "khoa", defaultValue = "",required = false) String khoa,
                                         @RequestParam(value = "trangThai", defaultValue = "", required = false) String trangThai,
                                         @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        NhaTruong nt = nhaTruongService.getNhaTruong("NT001");
        NhaTruongDto nhaTruongDto = NhaTruongMapper.toDto(nt);

        List<SinhVienDto> allSinhViens = nhaTruongDto.getSinhViens().stream()
                .filter(sv -> sv.getTrangThai() == null || sv.getTrangThai().equals("DUNG")) // điều kiện chung
                .filter(sv -> khoa.isEmpty() || sv.getKhoa().equalsIgnoreCase(khoa)) // lọc theo khoa
                .filter(sv -> trangThai.isEmpty() ||
                        (trangThai.equals("null") && sv.getTrangThai() == null) ||
                        (sv.getTrangThai() != null && sv.getTrangThai().toString().equalsIgnoreCase(trangThai))) // lọc theo trạng thái
                .filter(sv -> keyword.isEmpty() ||
                        sv.getMaSinhVien().toLowerCase().contains(keyword.toLowerCase()) ||
                        sv.getHoVaTen().toLowerCase().contains(keyword.toLowerCase())) // tìm kiếm theo keyword
                .collect(Collectors.toList());

        Set<String> khoas = nt.getSinhViens().stream()
                .map(sv -> sv.getKhoa().toLowerCase())
                .collect(Collectors.toSet());

        int start = page * limit;
        int end = Math.min(start + limit, allSinhViens.size());

        List<SinhVienDto> sinhViensPhanTrang = allSinhViens.subList(start, end);
        int totalPages = (int) Math.ceil((double) allSinhViens.size() / limit);

        model.addAttribute("nhaTruong", nt);
        model.addAttribute("nhaTruongDto", nhaTruongDto);
        model.addAttribute("sinhViens", sinhViensPhanTrang);
        model.addAttribute("khoas",khoas);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("limit", limit);
        model.addAttribute("khoaSelected", khoa);
        model.addAttribute("trangThaiSelected", trangThai);
        model.addAttribute("keyword", keyword);
        return "school/quan-ly-sinh-vien";
    }

    @PostMapping("nha-truong/thong-tin/update")
    public String updateNhaTruong(@ModelAttribute("nhaTruongDto") NhaTruongDto dto,
                                  RedirectAttributes redirectAttributes) {
        try {
            NhaTruong nt = nhaTruongService.getNhaTruong(dto.getMaNhaTruong());
            if(dto.getLogoFile() != null && !dto.getLogoFile().getOriginalFilename().isBlank()) {
                String strUrl = fileService.store(dto.getLogoFile());
                nt.setBieuTuong(strUrl);
            }
            nt.setTenTruong(dto.getTenTruong());
            nt.setDiaChi(String.format("%s, %s, %s", dto.getChiTietDiaChi(), dto.getXa(), dto.getHuyen()));
            nt.setSoDienThoai(dto.getSoDienThoai());
            nt.setMaSoThue(dto.getMaSoThue());
            nt.setCapNhatVaoLuc(LocalDateTime.now());
            nhaTruongRepository.save(nt);
            redirectAttributes.addFlashAttribute("successMsg","Cập nhật thông tin thành công");
            return "redirect:/nha-truong/thong-tin";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg","Cập nhật thông tin thất bại");
            return "redirect:/nha-truong/thong-tin";
        }
    }

    @PostMapping("/nha-truong/sinh-vien/xac-nhan")
    public String xacNhanSinhVien(RedirectAttributes redirectAttributes,
                                  @RequestParam String maSinhVien,
                                  @RequestParam String trangThai){
        try {
            SinhVien sinhVien = sinhVienRepository.findById(maSinhVien)
                    .orElseThrow(()->new EntityNotFoundException("Sinh viên không tồn tại"));
            if(trangThai.equals("dung")){
                sinhVien.setTrangThai(TrangThaiSinhVien.DUNG);
                redirectAttributes.addFlashAttribute("successMsg","Sinh viên đã được xác nhận thành công");
            } else if (trangThai.equals("sai")) {
                sinhVien.setTrangThai(TrangThaiSinhVien.SAI);
                redirectAttributes.addFlashAttribute("successMsg","Bạn đã từ chối sinh viên");
            }
            sinhVien.setCapNhatVaoLuc(LocalDateTime.now());
            sinhVienRepository.save(sinhVien);
            return "redirect:/nha-truong/quan-ly-sinh-vien";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/nha-truong/quan-ly-sinh-vien";
        }

    }
}
