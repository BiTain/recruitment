package com.graduate.recruitment.service.business;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import com.graduate.recruitment.specification.SinhVienBaiDangSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResumeService {
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;

    public Page<SinhVienBaiDang> getAllResumeByStatus(String maDoanhNghiep,
                                                      String status,
                                                      Integer page,
                                                      Integer limit,
                                                      String maNhaTruong,
                                                      String maBaiDang,
                                                      String keyword,
                                                      String sapXepBy
                                                      ) {
        Pageable pageable = PageRequest.of(page, limit, buildSort(sapXepBy));
        KetQua trangThai = mapToTrangThaiUngTuyen(status);

        assert trangThai != null;

        Specification<SinhVienBaiDang> spec = SinhVienBaiDangSpecification.filter(
                maDoanhNghiep,
                trangThai,
                maNhaTruong,
                maBaiDang,
                keyword
        );

        return sinhVienBaiDangRepository.findAll(spec, pageable);
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

    private Sort buildSort(String sapXepBy) {
        if (sapXepBy == null || sapXepBy.isEmpty()) {
            return Sort.by(Sort.Direction.DESC, "taoVaoLuc");
        }
        return switch (sapXepBy) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "sinhVien.hoVaTen");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "sinhVien.hoVaTen");
            case "dateNewest" -> Sort.by(Sort.Direction.DESC, "taoVaoLuc");
            case "dateOldest" -> Sort.by(Sort.Direction.ASC, "taoVaoLuc");
            default -> Sort.unsorted();
        };
    }


}
