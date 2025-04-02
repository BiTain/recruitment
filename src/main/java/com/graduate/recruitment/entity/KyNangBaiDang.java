package com.graduate.recruitment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "KYNANG_BAIDANG")
@Getter
@Setter
public class KyNangBaiDang {
    @Id
    @ManyToOne
    @JoinColumn(name = "maKyNang")
    private KyNang kyNang;

    @Id
    @ManyToOne
    @JoinColumn(name = "maBaiDang")
    private BaiDang baiDang;

    @Column(name = "taoVaoLuc")
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;
}
