package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.repository.LichPhongVanRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.specification.LichPhongVanSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LichPhongVanService {
    private LichPhongVanRepository lichPhongVanRepository;
    private SinhVienRepository sinhVienRepository;

    public List<LichPhongVan> getAllLichPhongVan(String maSinhVien, String trangThai){
        SinhVien sinhVien = sinhVienRepository.findById(maSinhVien).orElseThrow();
        return lichPhongVanRepository.findAllBySinhVienAndTrangThai(sinhVien, TrangThaiPhongVan.valueOf(trangThai));
    }
}
