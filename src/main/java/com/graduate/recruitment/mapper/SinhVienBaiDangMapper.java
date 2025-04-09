package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.SinhVienBaiDangDto;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SinhVienBaiDangMapper {
    private SinhVienMapper sinhVienMapper;
    private BaiDangMapper baiDangMapper;

    public SinhVienBaiDangDto toDto(SinhVienBaiDang sinhVienBaiDang){
        SinhVienBaiDangDto sinhVienBaiDangDto = new SinhVienBaiDangDto();
        sinhVienBaiDangDto.setSinhVien(sinhVienMapper.toDto(sinhVienBaiDang.getSinhVien()));
        sinhVienBaiDangDto.setBaiDang(baiDangMapper.toDto(sinhVienBaiDang.getBaiDang()));
        sinhVienBaiDangDto.setSoYeuLyLich(sinhVienBaiDang.getSoYeuLyLich());
        sinhVienBaiDangDto.setKetQua(sinhVienBaiDang.getKetQua().name());
        return sinhVienBaiDangDto;
    }
}
