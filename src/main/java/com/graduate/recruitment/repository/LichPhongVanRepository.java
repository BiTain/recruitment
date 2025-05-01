package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LichPhongVanRepository extends JpaRepository<LichPhongVan, String>, JpaSpecificationExecutor<LichPhongVan> {
    List<LichPhongVan> findAllBySinhVienAndTrangThai(SinhVien sinhVien, TrangThaiPhongVan trangThai);
}
