package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.LoiMoiThucTap;
import com.graduate.recruitment.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LoiMoiThucTapRepository extends JpaRepository<LoiMoiThucTap,String>, JpaSpecificationExecutor<LoiMoiThucTap> {
    List<LoiMoiThucTap> findAllBySinhVien(SinhVien sinhVien);
    List<LoiMoiThucTap> findAllByDoanhNghiep(DoanhNghiep doanhNghiep);
}
