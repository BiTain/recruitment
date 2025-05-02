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
//    LocalDateTime taoVaoLuc;
//    LocalDateTime capNhatVaoLuc;
    List<BaiDangDto> baiDangs;
//    List<LichPhongVan> lichPhongVans;
//    List<LoiMoiThucTap> loiMoiThucTaps;
}
