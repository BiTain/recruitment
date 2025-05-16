package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.dto.TaiKhoanDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.DoanhNghiep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


public class DoanhNghiepMapper {
    public static DoanhNghiepDto toDto(DoanhNghiep doanhNghiep) {
        DoanhNghiepDto doanhNghiepDto = new DoanhNghiepDto();
        doanhNghiepDto.setMaDoanhNghiep(doanhNghiep.getMaDoanhNghiep());
        doanhNghiepDto.setTenDoanhNghiep(doanhNghiep.getTenDoanhNghiep());
        doanhNghiepDto.setDiaChi(doanhNghiep.getDiaChi());

        String diaChi = doanhNghiep.getDiaChi(); // Chuỗi đầy đủ
        String[] parts = diaChi.split(",\\s*");

        int len = parts.length;
        String quan = len >= 1 ? parts[len - 1] : "";
        String xa = len >= 2 ? parts[len - 2] : "";
        String chiTiet = len >= 3 ? String.join(", ", Arrays.copyOfRange(parts, 0, len - 2)) : "";

        doanhNghiepDto.setHuyen(quan);
        doanhNghiepDto.setXa(xa);
        doanhNghiepDto.setChiTietDiaChi(chiTiet);

        doanhNghiepDto.setLinhVuc(doanhNghiep.getLinhVuc());
        doanhNghiepDto.setMoHinh(doanhNghiep.getMoHinh());
        doanhNghiepDto.setMoTa(doanhNghiep.getMoTa());
        doanhNghiepDto.setPhucLoi(doanhNghiep.getPhucLoi());
        doanhNghiepDto.setMaSoThue(doanhNghiep.getMaSoThue());
        doanhNghiepDto.setTrangDoanhNghiep(doanhNghiep.getTrangDoanhNghiep());
        doanhNghiepDto.setAnhDaiDien(doanhNghiep.getAnhDaiDien());
        doanhNghiepDto.setSoDienThoai(doanhNghiep.getSoDienThoai());
        doanhNghiepDto.setBaiDangs(mapBaiDangs(doanhNghiep.getBaiDangs()));
        doanhNghiepDto.setTaiKhoan(TaiKhoanDto.builder()
                .maTaiKhoan(doanhNghiep.getTaiKhoan().getMaTaiKhoan())
                .email(doanhNghiep.getTaiKhoan().getEmail())
                .build());
        return doanhNghiepDto;
    }

    private static List<BaiDangDto> mapBaiDangs(List<BaiDang> baiDangs) {
        return baiDangs.stream().map(BaiDangMapper::toDto).toList();
    }
}
