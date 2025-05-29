package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SinhVienBaiDangRepository extends JpaRepository<SinhVienBaiDang,String>, JpaSpecificationExecutor<SinhVienBaiDang> {
    @Query("SELECT svbd FROM SinhVienBaiDang svbd WHERE svbd.sinhVien.maSinhVien = :maSinhVien ORDER BY svbd.capNhatVaoLuc DESC")
    List<SinhVienBaiDang> findBaiDangsByMaSinhVien(@Param("maSinhVien") String maSinhVien);
    @Query("SELECT s FROM SinhVienBaiDang s WHERE s.baiDang.doanhNghiep.maDoanhNghiep = :maDoanhNghiep AND s.ketQua = :ketQua")
    Page<SinhVienBaiDang> findByMaDoanhNghiepAndKetQua(String maDoanhNghiep, KetQua ketQua, Pageable pageable);
}
