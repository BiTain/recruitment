package com.graduate.recruitment.config;

import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository; // tạo riêng interface này

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        TaiKhoan tk = taiKhoanRepository.findByEmail(email);
        if(tk == null) {
            throw new UsernameNotFoundException("Thông tin đăng nhập không chính xác");
        }

        return new org.springframework.security.core.userdetails.User(
                tk.getEmail(),
                tk.getMatKhau(),
                Collections.emptyList()
        );
    }
}