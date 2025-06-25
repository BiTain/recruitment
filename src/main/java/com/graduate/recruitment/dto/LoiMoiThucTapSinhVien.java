package com.graduate.recruitment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoiMoiThucTapSinhVien {
    String tenDoanhNghiep;
    String viTriThucTap;
    String loaiThucTap;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
}
