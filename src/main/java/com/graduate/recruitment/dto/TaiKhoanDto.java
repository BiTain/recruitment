package com.graduate.recruitment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaiKhoanDto {
    private String maTaiKhoan;
    private String email;

    public TaiKhoanDto() {
        // constructor mặc định
    }

    @Builder
    public TaiKhoanDto(String maTaiKhoan, String email) {
        this.maTaiKhoan = maTaiKhoan;
        this.email = email;
    }
}
