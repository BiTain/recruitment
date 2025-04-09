package com.graduate.recruitment.entity;

import com.graduate.recruitment.entity.enums.Loai;
import com.graduate.recruitment.entity.enums.TrangThaiThucTap;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "LOIMOITHUCTAP")
@Getter
@Setter
public class LoiMoiThucTap {
    @Id
    @Column(name = "maLMTT")
    private String maLMTT;

    @ManyToOne
    @JoinColumn(name = "maSinhVien")
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "maDoanhNghiep")
    private DoanhNghiep doanhNghiep;

    @Column(name = "viTriThucTap")
    private String viTriThucTap;

    @Column(name = "tuNgay")
    private LocalDate tuNgay;

    @Column(name = "denNgay")
    private LocalDate denNgay;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "loaiThucTap")
    @Enumerated(EnumType.STRING)
    private Loai loaiThucTap;

    @Column(name = "quyenLoi", columnDefinition = "TEXT")
    private String quyenLoi;

    @Column(name = "trangThai")
    @Enumerated(EnumType.STRING)
    private TrangThaiThucTap trangThai;

    @Column(name = "hanXacNhan")
    private LocalDateTime hanXacNhan;

    @Column(name = "taoVaoLuc")
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;
}
