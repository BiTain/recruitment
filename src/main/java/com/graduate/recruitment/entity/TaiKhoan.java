package com.graduate.recruitment.entity;

import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import com.graduate.recruitment.entity.enums.VaiTro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TAIKHOAN")
@Getter
@Setter
public class TaiKhoan {
    @Id
    @Column(name = "maTaiKhoan")
    private String maTaiKhoan;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "vaiTro")
    @Enumerated(EnumType.STRING)
    private VaiTro vaiTro; // Enum: 1: Sinh Vien, 2: Nha Truong, 3: NTD, 4: Admin

    @Column(name = "trangThai")
    @Enumerated(EnumType.STRING)
    private TrangThaiTaiKhoan trangThai; // Enum: 0: Khong hoat dong, 1: hoat dong

    @Column(name = "taoVaoLuc")
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;
}
