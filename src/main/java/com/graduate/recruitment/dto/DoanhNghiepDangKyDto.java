package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoanhNghiepDangKyDto {
    String email;
    String password;
    String tenDoanhNghiep;
    String quan;
    String phuong;
    String diaChi;
    String soDienThoai;
    String maSoThue;
    String trangDoanhNghiep;
    MultipartFile giayPhep;
}
