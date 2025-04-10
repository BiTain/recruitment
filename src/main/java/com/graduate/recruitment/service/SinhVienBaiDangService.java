package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.SinhVienBaiDangDto;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.mapper.SinhVienBaiDangMapper;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SinhVienBaiDangService {

    private SinhVienBaiDangRepository sinhVienBaiDangRepository;

    public List<SinhVienBaiDangDto> getBaiDangApplied(String maSinhVien){
        List<SinhVienBaiDang> sinhVienBaiDangs = sinhVienBaiDangRepository.findBaiDangsByMaSinhVien(maSinhVien);
        return sinhVienBaiDangs.stream().map(SinhVienBaiDangMapper::toDto).toList();
    }
}
