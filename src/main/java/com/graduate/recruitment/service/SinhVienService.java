package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.repository.SinhVienRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SinhVienService {
    private SinhVienRepository sinhVienRepository;

    public SinhVienDto getByMaSinhVien(String maSinhVien){
        return null;
    }
}
