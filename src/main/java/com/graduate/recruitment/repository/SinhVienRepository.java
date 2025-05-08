package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinhVienRepository extends JpaRepository<SinhVien,String> {
    SinhVien findByTaiKhoan(TaiKhoan taiKhoan);
}
