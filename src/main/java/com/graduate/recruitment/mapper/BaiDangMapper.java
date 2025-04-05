package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.KyNangDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.entity.KyNangBaiDang;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BaiDangMapper {
    private KyNangMapper kyNangMapper;
    public BaiDangDto toDto(BaiDang baiDang){
        BaiDangDto baiDangDto = new BaiDangDto();
        baiDangDto.setMaBaiDang(baiDang.getMaBaiDang());
        baiDangDto.setLoai(baiDang.getLoai().name());
        baiDangDto.setTieuDe(baiDang.getTieuDe());
        baiDangDto.setMoTa(baiDang.getMoTa());
        baiDangDto.setYeuCau(baiDang.getYeuCau());
        baiDangDto.setQuyenLoi(baiDang.getQuyenLoi());
        baiDangDto.setDiaChi(baiDang.getDiaChi());
        baiDangDto.setTrangThai(baiDang.getTrangThai().name());
        baiDangDto.setDenHan(baiDang.getDenHan());
        baiDangDto.setKyNangBaiDangs(mapKyNangs(baiDang.getKyNangBaiDangs()));
        return baiDangDto;
    }

    private List<KyNangDto> mapKyNangs(List<KyNangBaiDang> kyNangBaiDangs){
        return kyNangBaiDangs.stream().map(kyNang -> {
            return kyNangMapper.toDto(kyNang.getKyNang());
        }).toList();
    }
}
