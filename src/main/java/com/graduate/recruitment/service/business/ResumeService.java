package com.graduate.recruitment.service.business;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResumeService {
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;

    public Page<SinhVienBaiDang> getAllResumeByStatus(String maDoanhNghiep, String status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        KetQua trangThai = mapToTrangThaiUngTuyen(status);

        assert trangThai != null;
        return sinhVienBaiDangRepository.findByMaDoanhNghiepAndKetQua(maDoanhNghiep, trangThai, pageable);
    }

    private KetQua mapToTrangThaiUngTuyen(String status) {
        switch (status) {
            case "dang-cho":
                return KetQua.DANG_CHO;
            case "da-thong-qua":
                return KetQua.THONG_QUA;
            case "da-tu-choi":
                return KetQua.TU_CHOI;
            default:
                return null;
        }
    }


}
