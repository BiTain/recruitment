package com.graduate.recruitment.entity;

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

    @Column(name = "ngayPhongVan")
    private LocalDateTime ngayPhongVan;

    @Column(name = "diaDiem")
    private String diaDiem;

    @Column(name = "trangThai")
    @Enumerated(EnumType.STRING)
    private TrangThaiPhongVan trangThai; // Enum: 0: Tu Choi Phong Van, 1: Dong Y Phong Van

    @Column(name = "taoVaoLuc")
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;
}
