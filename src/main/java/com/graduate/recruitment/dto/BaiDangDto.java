package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
    String yeuCau;
    String moTa;
    LocalDateTime denHan;
    String quyenLoi;
    String loai;
    String trangThai;
    String tenDoanhNghiep;
    List<String> maKyNangs;
    List<KyNangDto> kyNangBaiDangs;
    LocalDateTime taoVaoLuc;
}
