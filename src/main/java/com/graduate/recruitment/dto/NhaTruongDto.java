package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhaTruongDto {
    String maNhaTruong;
    String tenTruong;
    String diaChi;
    String soDienThoai;
    String maSoThue;
    List<SinhVienDto> sinhViens;
}
