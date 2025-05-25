package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LichPhongVanRepository extends JpaRepository<LichPhongVan, String>, JpaSpecificationExecutor<LichPhongVan> {
    List<LichPhongVan> findAllBySinhVienAndTrangThai(SinhVien sinhVien, TrangThaiPhongVan trangThai);
    List<LichPhongVan> findAllBySinhVien(SinhVien sinhVien);
    List<LichPhongVan> findByTrangThaiAndNgayPhongVanBefore(TrangThaiPhongVan trangThaiPhongVan,LocalDateTime ngay);
    List<LichPhongVan> findByTrangThaiAndHanXacNhanBefore(TrangThaiPhongVan trangThaiPhongVan,LocalDateTime ngay);
    Page<LichPhongVan> findAllByDoanhNghiepAndTrangThai(DoanhNghiep doanhNghiep, TrangThaiPhongVan trangThai, Pageable pageable);
}
