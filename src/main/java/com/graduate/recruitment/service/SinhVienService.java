package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.mapper.SinhVienMapper;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SinhVienService {
    private SinhVienRepository sinhVienRepository;
    private TaiKhoanRepository taiKhoanRepository;

    public SinhVienDto getByMaSinhVien(String maSinhVien){
        SinhVien sinhVien = sinhVienRepository.findById(maSinhVien).orElseThrow();
        return SinhVienMapper.toDto(sinhVien);
    }

    public SinhVien getByMaTaiKhoan(String maTaiKhoan){
        TaiKhoan taiKhoan = taiKhoanRepository.findById(maTaiKhoan)
                .orElseThrow(()->new EntityNotFoundException("Không tìm thấy tài khoản"));

        return sinhVienRepository.findByTaiKhoan(taiKhoan);
    }
}
