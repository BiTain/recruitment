package com.graduate.recruitment.service;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.config.MyUserDetails;
import com.graduate.recruitment.dto.DoanhNghiepDangKyDto;
import com.graduate.recruitment.dto.NhaTruongDangKyDto;
import com.graduate.recruitment.dto.SinhVienDangKyDto;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.entity.enums.VaiTro;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private TaiKhoanRepository taiKhoanRepository;
    private EmailService emailService;
    private SinhVienRepository sinhVienRepository;
    private NhaTruongRepository nhaTruongRepository;
    private FileService fileService;
    private DoanhNghiepRepository doanhNghiepRepository;

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
        taiKhoan.setTaoVaoLuc(LocalDateTime.now());
        taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
        taiKhoanRepository.save(taiKhoan);

        emailService.sendVerificationEmail(taiKhoan);
    }

    public void nhaTruongDangKy(NhaTruongDangKyDto dto){
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
        taiKhoan.setVaiTro(VaiTro.NHA_TRUONG);
        taiKhoan.setTaoVaoLuc(LocalDateTime.now());
        taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());
        taiKhoan = taiKhoanRepository.save(taiKhoan);

        if(nhaTruongRepository.existsByMaSoThue(dto.getMaSoThue())){
            throw new RuntimeException("Mã số thuế đã được sử dụng ");
        }

        if(nhaTruongRepository.existsBySoDienThoai(dto.getSoDienThoai())){
            throw new RuntimeException("Số điện thoại đã được sử dụng");
        }

        NhaTruong nhaTruong =  new NhaTruong();
        long sizeNhaTruong = nhaTruongRepository.count();
        nhaTruong.setMaNhaTruong(String.format("NT%03d", sizeNhaTruong + 1));
        nhaTruong.setTenTruong(dto.getTenTruong());
        nhaTruong.setDiaChi(String.format("%s, %s, %s", dto.getDiaChi(), dto.getPhuong(),dto.getQuan()));
        nhaTruong.setSoDienThoai(dto.getSoDienThoai());
        nhaTruong.setMaSoThue(dto.getMaSoThue());
        nhaTruong.setGiayPhep(fileService.store(dto.getGiayPhep()));
        nhaTruong.setTaiKhoan(taiKhoan);
        nhaTruong.setTaoVaoLuc(LocalDateTime.now());
        nhaTruong.setCapNhatVaoLuc(LocalDateTime.now());
        nhaTruongRepository.save(nhaTruong);
    }

    public void doanhNghiepDangKy(DoanhNghiepDangKyDto dto){
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
        taiKhoan.setVaiTro(VaiTro.NHA_TUYEN_DUNG);
        taiKhoan.setTaoVaoLuc(LocalDateTime.now());
        taiKhoan.setCapNhatVaoLuc(LocalDateTime.now());

        if(doanhNghiepRepository.existsByMaSoThue(dto.getMaSoThue())){
            throw new RuntimeException("Mã số thuế đã được sử dụng ");
        }

        if(doanhNghiepRepository.existsBySoDienThoai(dto.getSoDienThoai())){
            throw new RuntimeException("Số điện thoại đã được sử dụng");
        }

        DoanhNghiep doanhNghiep = new DoanhNghiep();
        long sizeDoanhNghiep = doanhNghiepRepository.count();
        doanhNghiep.setMaDoanhNghiep(String.format("DN%03d", sizeDoanhNghiep + 1));
        doanhNghiep.setTenDoanhNghiep(dto.getTenDoanhNghiep());
        doanhNghiep.setDiaChi(String.format("%s, %s, %s", dto.getDiaChi(), dto.getPhuong(),dto.getQuan()));
        doanhNghiep.setSoDienThoai(dto.getSoDienThoai());
        doanhNghiep.setTrangDoanhNghiep(dto.getTrangDoanhNghiep());
        doanhNghiep.setMaSoThue(dto.getMaSoThue());
        doanhNghiep.setGiayPhep(fileService.store(dto.getGiayPhep()));
        doanhNghiep.setTaiKhoan(taiKhoan);
        doanhNghiep.setTaoVaoLuc(LocalDateTime.now());
        doanhNghiep.setCapNhatVaoLuc(LocalDateTime.now());
        doanhNghiepRepository.save(doanhNghiep);
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
