package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoanhNghiepRepository extends JpaRepository<DoanhNghiep,String> {
    DoanhNghiep findByTaiKhoan(TaiKhoan taiKhoan);
}
