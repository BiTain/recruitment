package com.graduate.recruitment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DOANHNGHIEP")
@Getter
@Setter
public class DoanhNghiep {
    @Id
    @Column(name = "maDoanhNghiep")
    private String maDoanhNghiep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maTaiKhoan")
    private TaiKhoan taiKhoan;

    @Column(name = "tenDoanhNghiep")
    private String tenDoanhNghiep;

    @Column(name = "moHinh")
    private String moHinh;

    @Column(name = "linhVuc")
    private String linhVuc;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "soDienThoai", unique = true)
    private String soDienThoai;

    @Column(name = "maSoThue", unique = true)
    private String maSoThue;

    @Column(name = "trangDoanhNghiep")
    private String trangDoanhNghiep;

    @Column(name = "anhDaiDien")
    private String anhDaiDien;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "phucLoi", columnDefinition = "TEXT")
    private String phucLoi;

    @Column(name = "taoVaoLuc",updatable = false)
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;

    @OneToMany(mappedBy = "doanhNghiep")
    private List<BaiDang> baiDangs;

    @OneToMany(mappedBy = "doanhNghiep")
    private List<LichPhongVan> lichPhongVans;

    @OneToMany(mappedBy = "doanhNghiep")
    private List<LoiMoiThucTap> loiMoiThucTaps;
}
