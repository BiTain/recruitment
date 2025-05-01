package com.graduate.recruitment.dto;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.HinhThucPhongVan;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LichPhongVanDto {
    String maSinhVien;
    String maDoanhNghiep;
    String viTriPhongVan;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime ngayPhongVan;
    String diaDiem;
    String hinhThucPhongVan;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime hanXacNhan;
}
