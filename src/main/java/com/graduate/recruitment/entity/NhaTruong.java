package com.graduate.recruitment.entity;

import com.graduate.recruitment.entity.TaiKhoan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "NHATRUONG")
@Getter
@Setter
public class NhaTruong {
    @Id
    @Column(name = "maNhaTruong")
    private String maNhaTruong;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maTaiKhoan")
    private TaiKhoan taiKhoan;

    @Column(name = "tenTruong")
    private String tenTruong;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "soDienThoai", unique = true)
    private String soDienThoai;

    @Column(name = "maSoThue", unique = true)
    private String maSoThue;

    @Column(name = "bieuTuong")
    private String bieuTuong;

    @Column(name = "giayPhep")
    private String giayPhep;

    @Column(name = "taoVaoLuc",updatable = false)
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;

    @OneToMany(mappedBy = "nhaTruong")
    private List<SinhVien> sinhViens;
}
