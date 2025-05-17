package com.graduate.recruitment.controller;

import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.repository.LichPhongVanRepository;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import com.graduate.recruitment.service.LichPhongVanService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class LichPhongVanController {
    private LichPhongVanRepository lichPhongVanRepository;
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;

    @PostMapping("/sinh-vien/lich-phong-van/{maLPV}")
    public String xacNhanLMTT(RedirectAttributes redirectAttributes,
                              @PathVariable("maLPV") String maLPV,
                              @RequestParam("maHoSo") String maHoSo,
                              @RequestParam("trangThai") String trangThai) {
        try {
            LichPhongVan lichPhongVan = lichPhongVanRepository.findById(maLPV)
                    .orElseThrow(() -> new EntityNotFoundException("Không tồn tại lịch phỏng vấn"));
            SinhVienBaiDang hoSo = sinhVienBaiDangRepository.findById(maHoSo)
                    .orElseThrow(() -> new EntityNotFoundException("Không tồn tại hồ sơ"));
            if (trangThai.equals("chap-nhan")) {
                lichPhongVan.setTrangThai(TrangThaiPhongVan.DONG_Y);
                redirectAttributes.addFlashAttribute("successMsg",
                        "Bạn đã xác nhận tham gia phỏng vấn thành công!");
            } else if (trangThai.equals("tu-choi")) {
                lichPhongVan.setTrangThai(TrangThaiPhongVan.TU_CHOI);
                hoSo.setKetQua(KetQua.TU_CHOI);
                hoSo.setCapNhatVaoLuc(LocalDateTime.now());
                sinhVienBaiDangRepository.save(hoSo);
                redirectAttributes.addFlashAttribute("successMsg",
                        "Bạn đã từ chối tham gia phỏng vấn!");
            }
            lichPhongVan.setCapNhatVaoLuc(LocalDateTime.now());
            lichPhongVanRepository.save(lichPhongVan);
            return "redirect:/sinh-vien/lich-phong-van";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/sinh-vien/lich-phong-van";
        }
    }
}
