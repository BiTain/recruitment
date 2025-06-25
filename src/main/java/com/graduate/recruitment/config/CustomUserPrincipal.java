package com.graduate.recruitment.config;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
public class CustomUserPrincipal implements UserDetails, OAuth2User {
    private TaiKhoan taiKhoan;
    private SinhVien sinhVien;
    private DoanhNghiep doanhNghiep;
    private NhaTruong nhaTruong;
    private Map<String, Object> attributes; // Dành cho OAuth2
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor cho form login
    public CustomUserPrincipal(TaiKhoan taiKhoan, SinhVien sinhVien,
                               Collection<? extends GrantedAuthority> authorities) {
        this.taiKhoan = taiKhoan;
        this.sinhVien = sinhVien;
        this.authorities = authorities;
    }

    public CustomUserPrincipal(TaiKhoan taiKhoan, DoanhNghiep doanhNghiep,
                               Collection<? extends GrantedAuthority> authorities) {
        this.taiKhoan = taiKhoan;
        this.doanhNghiep = doanhNghiep;
        this.authorities = authorities;
    }

    public CustomUserPrincipal(TaiKhoan taiKhoan, NhaTruong nhaTruong,
                               Collection<? extends GrantedAuthority> authorities) {
        this.taiKhoan = taiKhoan;
        this.nhaTruong = nhaTruong;
        this.authorities = authorities;
    }

    public CustomUserPrincipal(TaiKhoan taiKhoan,Collection<? extends GrantedAuthority> authorities){
        this.taiKhoan = taiKhoan;
        this.authorities = authorities;
    }

    // Constructor cho OAuth2 login
    public CustomUserPrincipal(TaiKhoan taiKhoan, SinhVien sinhVien,
                               Collection<? extends GrantedAuthority> authorities,
                               Map<String, Object> attributes) {
        this.taiKhoan = taiKhoan;
        this.sinhVien = sinhVien;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    // --- UserDetails methods ---
    @Override
    public String getUsername() {
        return taiKhoan.getEmail();
    }

    @Override
    public String getPassword() {
        return taiKhoan.getMatKhau(); // có thể null nếu là OAuth2
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    // --- OAuth2User methods ---
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return taiKhoan.getEmail();
    }
}

