package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienDto {
    String maSinhVien;
    String maNhaTruong;
    String hoVaTen;
    LocalDate ngaySinh;
    String soDienThoai;
    String gioiTinh;
    String diaChi;
    String truong;
    String lop;
    String khoa;
    String chuyenNganh;
    String cccd;
    String trangThai;
    String email;
    List<SinhVienBaiDangDto> sinhVienBaiDangs;
}
