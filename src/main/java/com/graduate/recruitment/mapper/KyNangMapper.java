package com.graduate.recruitment.mapper;

import com.graduate.recruitment.dto.KyNangDto;
import com.graduate.recruitment.entity.KyNang;
import org.springframework.stereotype.Component;


public class KyNangMapper {
    public static KyNangDto toDto(KyNang kyNang){
        KyNangDto kyNangDto = new KyNangDto();
        kyNangDto.setMaKyNang(kyNang.getMaKyNang());
        kyNangDto.setTenKyNang(kyNang.getTenKyNang());
        return kyNangDto;
    }
}
