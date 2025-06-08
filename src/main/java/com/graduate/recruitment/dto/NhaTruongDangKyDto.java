package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhaTruongDangKyDto {
    String email;
    String password;
    String tenTruong;
    String quan;
    String phuong;
    String diaChi;
    String soDienThoai;
    String maSoThue;
    MultipartFile giayPhep;
}
