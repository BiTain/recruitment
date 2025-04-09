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
    String hoVaTen;
    LocalDate ngaySinh;
    String soDienThoai;
    String gioiTinh;
    String diaChi;
    String lop;
    String khoa;
    String chuyenNganh;
    String cccd;
    String trangThai;
    NhaTruongDto nhaTruong;
    List<SinhVienBaiDangDto> sinhVienBaiDangs;
}
