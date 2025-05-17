package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinhVienBaiDangDto {
    String maSinhVien;
    String maBaiDang;
    String tieuDe;
    String tenDoanhNghiep;
    String diaChi;
    String hoVaTen;
    String soDienThoai;
    String email;
    String soYeuLyLich;
    MultipartFile fileCV;
    String ketQua;
    String logoDoanhNghiep;
    LocalDateTime taoVaoLuc;
}
