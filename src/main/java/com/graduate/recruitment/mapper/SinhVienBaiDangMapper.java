package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.SinhVienBaiDangDto;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


public class SinhVienBaiDangMapper {

    public static SinhVienBaiDangDto toDto(SinhVienBaiDang sinhVienBaiDang){
        SinhVienBaiDangDto sinhVienBaiDangDto = new SinhVienBaiDangDto();
        sinhVienBaiDangDto.setMaSinhVien(sinhVienBaiDang.getSinhVien().getMaSinhVien());
        sinhVienBaiDangDto.setMaBaiDang(sinhVienBaiDang.getBaiDang().getMaBaiDang());
        sinhVienBaiDangDto.setTieuDe(sinhVienBaiDang.getBaiDang().getTieuDe());
        sinhVienBaiDangDto.setTenDoanhNghiep(sinhVienBaiDang.getBaiDang().getDoanhNghiep().getTenDoanhNghiep());
        sinhVienBaiDangDto.setDiaChi(sinhVienBaiDang.getBaiDang().getDiaChi());
        sinhVienBaiDangDto.setSoYeuLyLich(sinhVienBaiDang.getSoYeuLyLich());
        sinhVienBaiDangDto.setTaoVaoLuc(sinhVienBaiDang.getTaoVaoLuc());
        sinhVienBaiDangDto.setLogoDoanhNghiep(sinhVienBaiDang.getBaiDang().getDoanhNghiep().getAnhDaiDien());
        return sinhVienBaiDangDto;
    }


}
