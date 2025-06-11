package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaiDangDto {
    String maBaiDang;
    String maDanhMuc;
    String maDoanhNghiep;
    String tieuDe;
    String diaChi;
    String huyen;
    String phuong;
    String yeuCau;
    String yeuCauString;
    String quyenLoiString;
    String moTa;
    LocalDateTime denHan;
    LocalDate denHanUpdate;
    String quyenLoi;
    String loai;
    String trangThai;
    String anh;
    String tenDoanhNghiep;
    List<String> maKyNangs;
    String maKyNangsString;
    List<KyNangDto> kyNangBaiDangs;
    LocalDateTime taoVaoLuc;
}
