package com.graduate.recruitment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "KYNANG")
@Getter
@Setter
public class KyNang {
    @Id
    @Column(name = "maKyNang")
    private String maKyNang;

    @ManyToOne
    @JoinColumn(name = "maDanhMuc")
    private DanhMuc danhMuc;

    @Column(name = "tenKyNang")
    private String tenKyNang;

    @Column(name = "taoVaoLuc")
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;

    @OneToMany(mappedBy = "kyNang")
    private List<KyNangBaiDang> kyNangBaiDangs;
}
