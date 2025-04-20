package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SinhVienBaiDangRepository extends JpaRepository<SinhVienBaiDang,String> {
    @Query("SELECT svbd FROM SinhVienBaiDang svbd WHERE svbd.sinhVien.maSinhVien = :maSinhVien ORDER BY svbd.capNhatVaoLuc DESC")
    List<SinhVienBaiDang> findBaiDangsByMaSinhVien(@Param("maSinhVien") String maSinhVien);
    @Query("SELECT DISTINCT svbd FROM SinhVienBaiDang svbd JOIN svbd.baiDang bd WHERE bd.doanhNghiep.maDoanhNghiep = :maDoanhNghiep")
    Page<SinhVienBaiDang> findByMaDoanhNghiep(@Param("maDoanhNghiep") String maDoanhNghiep, Pageable pageable);
}
