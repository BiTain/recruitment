package com.graduate.recruitment.controller.school;

import com.graduate.recruitment.dto.NhaTruongDto;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.TrangThaiSinhVien;
import com.graduate.recruitment.mapper.NhaTruongMapper;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.service.FileService;
import com.graduate.recruitment.service.NhaTruongService;
import com.graduate.recruitment.service.SinhVienService;
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
        return "school/home";
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
            return "redirect:/nha-truong/thong-tin";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
            return "redirect:/nha-truong/thong-tin";
        }

    }
}
