package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoanhNghiepRepository extends JpaRepository<DoanhNghiep,String>, JpaSpecificationExecutor<DoanhNghiep> {
    DoanhNghiep findByTaiKhoan(TaiKhoan taiKhoan);
    Boolean existsByMaSoThue(String maSoThue);
    Boolean existsBySoDienThoai(String soDienThoai);
}
