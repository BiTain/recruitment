package com.graduate.recruitment.config;

import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.entity.enums.VaiTro;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private TaiKhoanRepository tkRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Lấy thông tin từ Google
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Kiểm tra xem user đã tồn tại trong DB chưa
        TaiKhoan tk = tkRepo.findByEmail(email);
        if (tk == null) {
            // Chưa có thì tạo mới
            TaiKhoan newTk = new TaiKhoan();
            long size = tkRepo.count();
            newTk.setMaTaiKhoan(String.format("TK%03d", size + 1));
            newTk.setEmail(email);
            newTk.setVaiTro(VaiTro.SINH_VIEN);
            tkRepo.save(newTk);
        }

        // Trả về một DefaultOAuth2User có role
        return new DefaultOAuth2User(
                Collections.emptyList(),
                oAuth2User.getAttributes(),
                "sub" // hoặc "email" tùy thuộc vào config
        );
    }
}
