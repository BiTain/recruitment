package com.graduate.recruitment.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoanhNghiepDto {
    String maDoanhNghiep;
    String tenDoanhNghiep;
    String diaChi;
    String moHinh;
    String linhVuc;
    String soDienThoai;
    String maSoThue;
    String trangDoanhNghiep;
    String anhDaiDien;
    String moTa;
    String phucLoi;
    TaiKhoanDto taiKhoan;
    List<BaiDangDto> baiDangs;
}
