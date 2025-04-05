package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.mapper.DoanhNghiepMapper;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoanhnghiepService {
    private DoanhNghiepRepository doanhNghiepRepository;
    private DoanhNghiepMapper doanhNghiepMapper;
    public DoanhNghiepDto getDoanhNghiepById(String maDoanhNghiep){
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(maDoanhNghiep).orElseThrow();
        return doanhNghiepMapper.toDto(doanhNghiep);
    }
}
