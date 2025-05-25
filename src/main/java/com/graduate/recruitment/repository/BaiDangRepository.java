package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BaiDangRepository extends JpaRepository<BaiDang,String>, JpaSpecificationExecutor<BaiDang> {
    Page<BaiDang> findByDoanhNghiep(DoanhNghiep doanhNghiep, Pageable pageable);
    List<BaiDang> findByTrangThaiNotInAndDenHanBefore(List<TrangThaiBaiDang> trangThaiList, LocalDateTime denHan);

}
