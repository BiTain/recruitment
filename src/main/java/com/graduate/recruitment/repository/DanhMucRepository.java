package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.entity.KyNang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DanhMucRepository extends JpaRepository<DanhMuc,String>, JpaSpecificationExecutor<DanhMuc> {
    Optional<DanhMuc> findByTenDanhMuc(String tenDanhMuc);

    @Query("SELECT d FROM DanhMuc d WHERE d.tenDanhMuc = :tenDanhMuc AND d.maDanhMuc <> :maDanhMuc")
    Optional<DanhMuc> findByTenDanhMucAndMaDanhMucNot(String tenDanhMuc, String maDanhMuc);

}
