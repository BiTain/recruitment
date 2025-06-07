package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.entity.enums.TrangThaiTaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhaTruongRepository extends JpaRepository<NhaTruong,String> {
    NhaTruong findByTaiKhoan(TaiKhoan taiKhoan);
    Page<NhaTruong> findByTaiKhoan_TrangThaiIn(List<TrangThaiTaiKhoan> trangThaiList, Pageable pageable);
    Page<NhaTruong> findByTaiKhoan_TrangThai(TrangThaiTaiKhoan trangThai, Pageable pageable);
    Page<NhaTruong> findByTaiKhoan_TrangThaiAndTenTruongContainingIgnoreCase(TrangThaiTaiKhoan status, String ten, Pageable pageable);
    Page<NhaTruong> findByTenTruongContainingIgnoreCaseAndTaiKhoan_TrangThaiIn(String ten, List<TrangThaiTaiKhoan> statuses, Pageable pageable);
    Page<NhaTruong> findByTenTruongContainingIgnoreCaseAndTaiKhoan_TrangThai(
            String keyword, TrangThaiTaiKhoan trangThai, Pageable pageable);

    List<NhaTruong> findAllByTaiKhoan_TrangThaiIn(List<TrangThaiTaiKhoan> trangThais);
}
