package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.DoanhNghiep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


public class DoanhNghiepMapper {
    public static DoanhNghiepDto toDto(DoanhNghiep doanhNghiep){
        DoanhNghiepDto doanhNghiepDto = new DoanhNghiepDto();
        doanhNghiepDto.setMaDoanhNghiep(doanhNghiep.getMaDoanhNghiep());
        doanhNghiepDto.setTenDoanhNghiep(doanhNghiep.getTenDoanhNghiep());
        doanhNghiepDto.setDiaChi(doanhNghiep.getDiaChi());
        doanhNghiepDto.setMoTa(doanhNghiep.getMoTa());
        doanhNghiepDto.setTrangDoanhNghiep(doanhNghiep.getTrangDoanhNghiep());
        doanhNghiepDto.setAnhDaiDien(doanhNghiep.getAnhDaiDien());
        doanhNghiepDto.setSoDienThoai(doanhNghiep.getSoDienThoai());
        doanhNghiepDto.setBaiDangs(mapBaiDangs(doanhNghiep.getBaiDangs()));
        return doanhNghiepDto;
    }

    private static List<BaiDangDto> mapBaiDangs(List<BaiDang> baiDangs){
        return baiDangs.stream().map(BaiDangMapper::toDto).toList();
    }
}
