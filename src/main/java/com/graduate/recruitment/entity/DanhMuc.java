package com.graduate.recruitment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DANHMUC")
@Getter
@Setter
public class DanhMuc {
    @Id
    @Column(name = "maDanhMuc")
    private String maDanhMuc;

    @Column(name = "tenDanhMuc")
    private String tenDanhMuc;

    @Column(name = "taoVaoLuc",updatable = false)
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;

    @OneToMany(mappedBy = "danhMuc")
    private List<KyNang> kyNangs;

    @OneToMany(mappedBy = "danhMuc")
    private List<BaiDang> baiDangs;
}
