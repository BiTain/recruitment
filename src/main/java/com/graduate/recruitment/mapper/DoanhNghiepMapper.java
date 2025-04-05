package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.DoanhNghiep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DoanhNghiepMapper {
    private BaiDangMapper baiDangMapper;
    public DoanhNghiepDto toDto(DoanhNghiep doanhNghiep){
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

    private List<BaiDangDto> mapBaiDangs(List<BaiDang> baiDangs){
        return baiDangs.stream().map(baiDang -> {
            return baiDangMapper.toDto(baiDang);
        }).toList();
    }
}
