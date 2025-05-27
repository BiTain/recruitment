package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SinhVienRepository extends JpaRepository<SinhVien,String>, JpaSpecificationExecutor<SinhVien> {
    SinhVien findByTaiKhoan(TaiKhoan taiKhoan);
}
