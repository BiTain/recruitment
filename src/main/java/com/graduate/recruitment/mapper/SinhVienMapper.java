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

@Component
@AllArgsConstructor
public class SinhVienMapper {
    private NhaTruongMapper nhaTruongMapper;
    private SinhVienBaiDangMapper sinhVienBaiDangMapper;
    public SinhVienDto toDto(SinhVien sinhVien){
        SinhVienDto sinhVienDto = new SinhVienDto();
        sinhVienDto.setMaSinhVien(sinhVien.getMaSinhVien());
        sinhVienDto.setHoVaTen(sinhVien.getHoVaTen());
        sinhVienDto.setNgaySinh(sinhVien.getNgaySinh());
        sinhVienDto.setSoDienThoai(sinhVien.getSoDienThoai());
        sinhVienDto.setGioiTinh(sinhVien.getGioiTinh().name());
        sinhVienDto.setDiaChi(sinhVien.getDiaChi());
        sinhVienDto.setLop(sinhVien.getLop());
        sinhVienDto.setKhoa(sinhVien.getKhoa());
        sinhVienDto.setChuyenNganh(sinhVien.getChuyenNganh());
        sinhVienDto.setCccd(sinhVien.getCccd());
        sinhVienDto.setTrangThai(sinhVien.getTrangThai().name());
        sinhVienDto.setNhaTruong(nhaTruongMapper.toDto(sinhVien.getNhaTruong()));
        sinhVienDto.setSinhVienBaiDangs(mapSinhVienBaiDangs(sinhVien.getSinhVienBaiDangs()));
        return sinhVienDto;
    }

    private List<SinhVienBaiDangDto> mapSinhVienBaiDangs(List<SinhVienBaiDang> sinhVienBaiDangs){
        return sinhVienBaiDangs.stream().map(sinhVienBaiDang -> {
            return sinhVienBaiDangMapper.toDto(sinhVienBaiDang);
        }).toList();
    }
}
