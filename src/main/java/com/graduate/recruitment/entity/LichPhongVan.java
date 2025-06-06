package com.graduate.recruitment.entity;

import com.graduate.recruitment.entity.enums.HinhThucPhongVan;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "LICHPHONGVAN")
@Getter
@Setter
public class LichPhongVan {
    @Id
    @Column(name = "maLichPhongVan")
    private String maLichPhongVan;

    @ManyToOne
    @JoinColumn(name = "maSinhVien")
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "maDoanhNghiep")
    private DoanhNghiep doanhNghiep;

    @Column(name = "maSVBD")
    private String maSVBD;

    @Column(name = "viTriPhongVan")
    private String viTriPhongVan;

    @Column(name = "ngayPhongVan")
    private LocalDateTime ngayPhongVan;

    @Column(name = "diaDiem")
    private String diaDiem;

    @Column(name = "soYeuLyLich")
    private String soYeuLyLich;

    @Column(name = "hinhThucPhongVan")
    @Enumerated(EnumType.STRING)
    private HinhThucPhongVan hinhThucPhongVan;

    @Column(name = "trangThai")
    @Enumerated(EnumType.STRING)
    private TrangThaiPhongVan trangThai;

    @Column(name = "hanXacNhan")
    private LocalDateTime hanXacNhan;

    @Column(name = "taoVaoLuc",updatable = false)
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;
}
