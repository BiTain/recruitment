package com.graduate.recruitment.dto;

import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.KyNangBaiDang;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.Loai;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
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
public class BaiDangDto {
    String maBaiDang;
    String tieuDe;
    String diaChi;
    String yeuCau;
    String moTa;
    LocalDateTime denHan;
    String quyenLoi;
    String loai;
    String trangThai;
    String tenDoanhNghiep;
    List<KyNangDto> kyNangBaiDangs;
}
