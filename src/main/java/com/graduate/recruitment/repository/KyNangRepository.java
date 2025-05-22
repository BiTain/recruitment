package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.KyNang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KyNangRepository extends JpaRepository<KyNang,String> {
    List<KyNang> findKyNangByTenKyNang(String tenKyNang);

    // KyNangRepository.java
    @Query("SELECT k FROM KyNang k WHERE k.tenKyNang = :tenKyNang AND k.maKyNang <> :maKyNang")
    Optional<KyNang> findByTenKyNangAndMaKyNangNot(String tenKyNang, String maKyNang);

}
