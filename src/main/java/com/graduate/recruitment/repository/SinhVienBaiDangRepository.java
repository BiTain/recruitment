package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SinhVienBaiDangRepository extends JpaRepository<SinhVienBaiDang,String> {
    @Query("SELECT svbd FROM SinhVienBaiDang svbd WHERE svbd.sinhVien.maSinhVien = :maSinhVien ORDER BY svbd.capNhatVaoLuc DESC")
    List<SinhVienBaiDang> findBaiDangsByMaSinhVien(@Param("maSinhVien") String maSinhVien);
}
