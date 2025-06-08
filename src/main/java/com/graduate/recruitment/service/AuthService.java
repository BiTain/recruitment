package com.graduate.recruitment.service;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.config.MyUserDetails;
import com.graduate.recruitment.dto.SinhVienDangKyDto;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.entity.enums.VaiTro;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private TaiKhoanRepository taiKhoanRepository;
    private EmailService emailService;
    private SinhVienRepository sinhVienRepository;

    public void sinhVienDangKy(SinhVienDangKyDto dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu không khớp");
        }

        TaiKhoan existingTaiKhoan = taiKhoanRepository.findByEmail(dto.getEmail());
        if (existingTaiKhoan != null) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        long size = taiKhoanRepository.count();
        taiKhoan.setMaTaiKhoan(String.format("TK%03d", size + 1));
        taiKhoan.setEmail(dto.getEmail());
        taiKhoan.setMatKhau(passwordEncoder.encode(dto.getPassword()));
        taiKhoan.setTrangThai(TrangThaiTaiKhoan.KHONG_HOAT_DONG);
        taiKhoan.setVaiTro(VaiTro.SINH_VIEN);
        taiKhoanRepository.save(taiKhoan);

        emailService.sendVerificationEmail(taiKhoan);
    }

    public boolean xuLyDangNhap(String email, String password) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(email);
        if (taiKhoan == null) {
            return false;
        }

        if (!passwordEncoder.matches(password, taiKhoan.getMatKhau())) {
            return false;
        }

        SinhVien sinhVien = sinhVienRepository.findByTaiKhoan(taiKhoan);

        // Lưu thông tin đăng nhập vào SecurityContext
        CustomUserPrincipal principal = new CustomUserPrincipal(taiKhoan, sinhVien, Collections.emptyList());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(principal, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return true;
    }

    public boolean kichHoatTaiKhoan(String maTaiKhoan) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(maTaiKhoan).get();

        taiKhoan.setTrangThai(TrangThaiTaiKhoan.HOAT_DONG);
        taiKhoanRepository.save(taiKhoan);
        return true;
    }
}
