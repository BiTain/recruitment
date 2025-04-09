package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.NhaTruongDto;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.SinhVien;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NhaTruongMapper {
    private SinhVienMapper sinhVienMapper;
    public NhaTruongDto toDto(NhaTruong nhaTruong){
        NhaTruongDto nhaTruongDto = new NhaTruongDto();
        nhaTruongDto.setMaNhaTruong(nhaTruong.getMaNhaTruong());
        nhaTruongDto.setTenTruong(nhaTruong.getTenTruong());
        nhaTruongDto.setDiaChi(nhaTruong.getDiaChi());
        nhaTruongDto.setSoDienThoai(nhaTruong.getSoDienThoai());
        nhaTruongDto.setMaSoThue(nhaTruong.getMaSoThue());
        nhaTruongDto.setSinhViens(mapSinhViens(nhaTruong.getSinhViens()));
        return nhaTruongDto;
    }

    private List<SinhVienDto> mapSinhViens(List<SinhVien> sinhViens){
        return sinhViens.stream().map(sinhVien -> {
            return sinhVienMapper.toDto(sinhVien);
        }).toList();
    }
}
