package com.graduate.recruitment.entity;

import com.graduate.recruitment.entity.enums.Loai;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BAIDANG")
@Getter
@Setter
public class BaiDang {
    @Id
    @Column(name = "maBaiDang")
    private String maBaiDang;

    @ManyToOne
    @JoinColumn(name = "maDoanhNghiep")
    private DoanhNghiep doanhNghiep;

    @ManyToOne
    @JoinColumn(name = "maDanhMuc")
    private DanhMuc danhMuc;

    @Column(name = "tieuDe")
    private String tieuDe;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "yeuCau", columnDefinition = "TEXT")
    private String yeuCau;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "denHan")
    private LocalDateTime denHan;

    @Column(name = "quyenLoi", columnDefinition = "TEXT")
    private String quyenLoi;

    @Column(name = "loai")
    @Enumerated(EnumType.STRING)
    private Loai loai;

    @Column(name = "trangThai")
    @Enumerated(EnumType.STRING)
    private TrangThaiBaiDang trangThai;

    @Column(name = "taoVaoLuc",updatable = false)
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;

    @OneToMany(mappedBy = "baiDang")
    private List<KyNangBaiDang> kyNangBaiDangs;

    @OneToMany(mappedBy = "baiDang")
    private List<SinhVienBaiDang> sinhVienBaiDangs;
}
