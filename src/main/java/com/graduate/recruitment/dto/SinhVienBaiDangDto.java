package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienBaiDangDto {
    SinhVienDto sinhVien;
    BaiDangDto baiDang;
    String soYeuLyLich;
    String ketQua;
}
