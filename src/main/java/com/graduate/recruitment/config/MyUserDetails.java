package com.graduate.recruitment.config;

import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class MyUserDetails extends User {
    private TaiKhoan taiKhoan;
    private SinhVien sinhVien;

    public MyUserDetails(
            TaiKhoan taiKhoan,
            SinhVien sinhVien,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.taiKhoan = taiKhoan;
        this.sinhVien = sinhVien;
    }
}
