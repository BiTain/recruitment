package com.graduate.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KyNangBaiDangId implements Serializable {
    private String kyNang;
    private String baiDang;
}
