package com.graduate.recruitment.dto;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.LoiMoiThucTap;
import com.graduate.recruitment.entity.TaiKhoan;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoanhNghiepDto {
    String maDoanhNghiep;
//    TaiKhoan taiKhoan;
    String tenDoanhNghiep;
    String diaChi;
    String soDienThoai;
//    String maSoThue;
    String trangDoanhNghiep;
    String anhDaiDien;
    String moTa;
//    LocalDateTime taoVaoLuc;
//    LocalDateTime capNhatVaoLuc;
    List<BaiDangDto> baiDangs;
//    List<LichPhongVan> lichPhongVans;
//    List<LoiMoiThucTap> loiMoiThucTaps;
}
