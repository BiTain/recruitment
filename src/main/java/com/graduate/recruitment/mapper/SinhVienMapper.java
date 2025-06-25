package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.LoiMoiThucTapSinhVien;
import com.graduate.recruitment.dto.NhaTruongDto;
import com.graduate.recruitment.dto.SinhVienBaiDangDto;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.LoiMoiThucTap;
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
        if (sinhVien.getTrangThai() == null){
            sinhVienDto.setTrangThai(null);
        }else{
            sinhVienDto.setTrangThai(sinhVien.getTrangThai().name());
        }
        sinhVienDto.setSinhVienBaiDangs(mapSinhVienBaiDangs(sinhVien.getSinhVienBaiDangs()));
        sinhVienDto.setEmail(sinhVien.getTaiKhoan().getEmail());
        sinhVienDto.setMaNhaTruong(sinhVien.getNhaTruong().getMaNhaTruong());
        sinhVienDto.setLoiMoiThucTapSinhViens(mapLoiMoiThucTap(sinhVien.getLoiMoiThucTaps()));
        return sinhVienDto;
    }

    private static List<SinhVienBaiDangDto> mapSinhVienBaiDangs(List<SinhVienBaiDang> sinhVienBaiDangs){
        return sinhVienBaiDangs.stream().map(SinhVienBaiDangMapper::toDto).toList();
    }

    private static List<LoiMoiThucTapSinhVien> mapLoiMoiThucTap(List<LoiMoiThucTap> loiMoiThucTapList){
        return loiMoiThucTapList.stream().map(lmtt ->{
            LoiMoiThucTapSinhVien loiMoiThucTapSinhVien = new LoiMoiThucTapSinhVien();
            loiMoiThucTapSinhVien.setTenDoanhNghiep(lmtt.getDoanhNghiep().getTenDoanhNghiep());
            loiMoiThucTapSinhVien.setViTriThucTap(lmtt.getViTriThucTap());
            loiMoiThucTapSinhVien.setLoaiThucTap(lmtt.getLoaiThucTap().name());
            loiMoiThucTapSinhVien.setNgayBatDau(lmtt.getTuNgay());
            loiMoiThucTapSinhVien.setNgayKetThuc(lmtt.getDenNgay());
            return loiMoiThucTapSinhVien;
        }).toList();
    }
}
