package com.graduate.recruitment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "KYNANG_BAIDANG")
@Getter
@Setter
@IdClass(KyNangBaiDangId.class)
public class KyNangBaiDang {
    @Id
    @ManyToOne
    @JoinColumn(name = "maKyNang")
    private KyNang kyNang;

    @Id
    @ManyToOne
    @JoinColumn(name = "maBaiDang")
    private BaiDang baiDang;

    @Column(name = "taoVaoLuc",updatable = false)
    private LocalDateTime taoVaoLuc;

    @Column(name = "capNhatVaoLuc")
    private LocalDateTime capNhatVaoLuc;
}
