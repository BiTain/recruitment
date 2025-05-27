package com.graduate.recruitment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaiKhoanDto {
    private String maTaiKhoan;
    private String email;
    private String matKhau;
    private String matKhauMoi;

    public TaiKhoanDto() {
        // constructor mặc định
    }

    @Builder
    public TaiKhoanDto(String maTaiKhoan, String email) {
        this.maTaiKhoan = maTaiKhoan;
        this.email = email;
    }
}
