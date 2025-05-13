package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.LichPhongVanDto;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.HinhThucPhongVan;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.LichPhongVanRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.specification.LichPhongVanSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LichPhongVanService {
    private LichPhongVanRepository lichPhongVanRepository;
    private SinhVienRepository sinhVienRepository;
    private DoanhNghiepRepository doanhNghiepRepository;

    public List<LichPhongVan> getAllLichPhongVan(String maSinhVien, String trangThai){
        SinhVien sinhVien = sinhVienRepository.findById(maSinhVien).orElseThrow();
        return lichPhongVanRepository.findAllBySinhVienAndTrangThai(sinhVien, TrangThaiPhongVan.valueOf(trangThai));
    }

    public Map<String, List<LichPhongVan>> getAllLichPhongVanByTrangThai(String maSinhVien) {
        SinhVien sinhVien = sinhVienRepository.findById(maSinhVien)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với mã: " + maSinhVien));

        // Lấy tất cả lịch phỏng vấn của sinh viên
        List<LichPhongVan> lichPhongVanList = lichPhongVanRepository.findAllBySinhVien(sinhVien);

        LocalDateTime now = LocalDateTime.now();

        // Phân loại lịch phỏng vấn
        Map<String, List<LichPhongVan>> result = new HashMap<>();

        // Phỏng vấn sắp tới: đã xác nhận và chưa diễn ra
        result.put("sap-toi", lichPhongVanList.stream()
                .filter(lpv -> lpv.getTrangThai() == TrangThaiPhongVan.DONG_Y
                        && lpv.getNgayPhongVan().isAfter(now))
                .collect(Collectors.toList()));

        // Phỏng vấn đang chờ xác nhận
        result.put("dang-cho", lichPhongVanList.stream()
                .filter(lpv -> lpv.getTrangThai() == TrangThaiPhongVan.DANG_CHO
                    && lpv.getHanXacNhan().isAfter(now))
                .collect(Collectors.toList()));

        // Phỏng vấn đã hoàn thành hoặc đã từ chối
        result.put("hoan-thanh", lichPhongVanList.stream()
                .filter(lpv -> lpv.getTrangThai() == TrangThaiPhongVan.HOAN_THANH
                        || lpv.getTrangThai() == TrangThaiPhongVan.TU_CHOI
                        || (lpv.getTrangThai() == TrangThaiPhongVan.DONG_Y && lpv.getNgayPhongVan().isBefore(now))
                        || (lpv.getTrangThai() == TrangThaiPhongVan.DANG_CHO && lpv.getHanXacNhan().isBefore(now)))
                .collect(Collectors.toList()));

        return result;
    }
}
