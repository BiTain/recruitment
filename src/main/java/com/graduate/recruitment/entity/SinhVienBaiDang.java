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
    @ManyToOne
    @JoinColumn(name = "maSinhVien")
    private SinhVien sinhVien;

    @Id
    @ManyToOne
    @JoinColumn(name = "maBaiDang")
    private BaiDang baiDang;

    @Column(name = "soYeuLyLich")
    private String soYeuLyLich;

    @Column(name = "ketQua")
    @Enumerated(EnumType.STRING)
    private KetQua ketQua; // Enum: 0: Ho So Bi Tu Choi, 1: Ho So Duoc Thong Qua

    @Column(name = "taoVaoLuc")
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;
}
