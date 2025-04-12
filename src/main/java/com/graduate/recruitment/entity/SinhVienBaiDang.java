package com.graduate.recruitment.entity;

import com.graduate.recruitment.entity.enums.KetQua;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "SINHVIEN_BAIDANG")
@Getter
@Setter
public class SinhVienBaiDang {

    @Id
    @Column(name = "maSVBD")
    private String maSVBD;

    @ManyToOne
    @JoinColumn(name = "maSinhVien")
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "maBaiDang")
    private BaiDang baiDang;

    @Column(name = "hoVaten")
    private String hoVaten;

    @Column(name = "soDienThoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "soYeuLyLich")
    private String soYeuLyLich;

    @Column(name = "ketQua")
    @Enumerated(EnumType.STRING)
    private KetQua ketQua;

    @Column(name = "taoVaoLuc",updatable = false)
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;
}
