package com.graduate.recruitment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class TaiKhoanDto {
    private String maTaiKhoan;
    private String email;
}
