package com.graduate.recruitment.service.business;

import com.graduate.recruitment.dto.SinhVienBaiDangDto;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.mapper.SinhVienBaiDangMapper;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeService {
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;

    public Page<SinhVienBaiDang> getAllResume(String maDoanhNghiep,Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        return sinhVienBaiDangRepository.findByMaDoanhNghiep(maDoanhNghiep,pageable);
    }


}
