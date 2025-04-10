package com.graduate.recruitment.entity;

import com.graduate.recruitment.entity.enums.GioiTinh;
import com.graduate.recruitment.entity.enums.TrangThaiSinhVien;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SINHVIEN")
@Getter
@Setter
public class SinhVien {
    @Id
    @Column(name = "maSinhVien")
    private String maSinhVien;

    @OneToOne
    @JoinColumn(name = "maTaiKhoan")
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "maNhaTruong")
    private NhaTruong nhaTruong;

    @Column(name = "hoVaTen")
    private String hoVaTen;

    @Column(name = "ngaySinh")
    private LocalDate ngaySinh;

    @Column(name = "soDienThoai", unique = true)
    private String soDienThoai;

    @Column(name = "gioiTinh")
    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "khoa")
    private String khoa;

    @Column(name = "lop")
    private String lop;

    @Column(name = "chuyenNganh")
    private String chuyenNganh;

    @Column(name = "CCCD")
    private String cccd;

    @Column(name = "trangThai")
    @Enumerated(EnumType.STRING)
    private TrangThaiSinhVien trangThai;

    @Column(name = "taoVaoLuc",updatable = false)
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;

    @OneToMany(mappedBy = "sinhVien")
    private List<SinhVienBaiDang> sinhVienBaiDangs;

    @OneToMany(mappedBy = "sinhVien")
    private List<LichPhongVan> lichPhongVans;

    @OneToMany(mappedBy = "sinhVien")
    private List<LoiMoiThucTap> loiMoiThucTaps;
}
