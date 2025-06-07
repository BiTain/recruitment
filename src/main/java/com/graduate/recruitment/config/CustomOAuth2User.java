package com.graduate.recruitment.config;

import com.graduate.recruitment.entity.TaiKhoan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oAuth2User;
    private TaiKhoan taiKhoan; // Thêm entity của bạn ở đây

    public CustomOAuth2User(OAuth2User oAuth2User, TaiKhoan taiKhoan) {
        this.oAuth2User = oAuth2User;
        this.taiKhoan = taiKhoan;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities(); // hoặc return List.of(new SimpleGrantedAuthority(...))
    }

    @Override
    public String getName() {
        return oAuth2User.getName();
    }
}
