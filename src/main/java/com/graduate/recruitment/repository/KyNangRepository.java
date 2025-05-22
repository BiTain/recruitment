package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.KyNang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KyNangRepository extends JpaRepository<KyNang,String> {
    List<KyNang> findKyNangByTenKyNang(String tenKyNang);
}
