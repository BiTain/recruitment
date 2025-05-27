package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhaTruongRepository extends JpaRepository<NhaTruong,String> {
    Page<NhaTruong> findByTaiKhoan_TrangThaiIn(List<TrangThaiTaiKhoan> trangThaiList, Pageable pageable);
    Page<NhaTruong> findByTaiKhoan_TrangThai(TrangThaiTaiKhoan trangThai, Pageable pageable);
}
