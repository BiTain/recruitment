package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.NhaTruongDto;
import com.graduate.recruitment.dto.SinhVienBaiDangDto;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


public class SinhVienMapper {
    public static SinhVienDto toDto(SinhVien sinhVien){
        SinhVienDto sinhVienDto = new SinhVienDto();
        sinhVienDto.setMaSinhVien(sinhVien.getMaSinhVien());
        sinhVienDto.setHoVaTen(sinhVien.getHoVaTen());
        sinhVienDto.setNgaySinh(sinhVien.getNgaySinh());
        sinhVienDto.setSoDienThoai(sinhVien.getSoDienThoai());
        sinhVienDto.setGioiTinh(sinhVien.getGioiTinh().name());
        sinhVienDto.setDiaChi(sinhVien.getDiaChi());
        sinhVienDto.setTruong(sinhVien.getNhaTruong().getTenTruong());
        sinhVienDto.setLop(sinhVien.getLop());
        sinhVienDto.setKhoa(sinhVien.getKhoa());
        sinhVienDto.setChuyenNganh(sinhVien.getChuyenNganh());
        sinhVienDto.setCccd(sinhVien.getCccd());
        sinhVienDto.setTrangThai(sinhVien.getTrangThai().name());
        sinhVienDto.setSinhVienBaiDangs(mapSinhVienBaiDangs(sinhVien.getSinhVienBaiDangs()));
        sinhVienDto.setEmail(sinhVien.getTaiKhoan().getEmail());
        return sinhVienDto;
    }

    private static List<SinhVienBaiDangDto> mapSinhVienBaiDangs(List<SinhVienBaiDang> sinhVienBaiDangs){
        return sinhVienBaiDangs.stream().map(SinhVienBaiDangMapper::toDto).toList();
    }
}
