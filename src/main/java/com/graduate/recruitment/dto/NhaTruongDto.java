package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhaTruongDto {
    String maNhaTruong;
    String tenTruong;
    String diaChi;
    String huyen;
    String xa;
    String chiTietDiaChi;
    String soDienThoai;
    String maSoThue;
    MultipartFile logoFile;
    String logoStr;
    List<SinhVienDto> sinhViens;
}
