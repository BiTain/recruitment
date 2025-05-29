package com.graduate.recruitment.specification;

import com.graduate.recruitment.entity.LoiMoiThucTap;
import com.graduate.recruitment.entity.enums.TrangThaiThucTap;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LoiMoiThucTapSpecification {

    public static Specification<LoiMoiThucTap> filterBy(
            String maDoanhNghiep,
            String keyword,
            String viTriThucTap,
            String maNhaTruong
    ) {
        return (root, query, criteriaBuilder) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            // Join liên kết giữa các bảng
            Join<?, ?> sinhVienJoin = root.join("sinhVien");
            Join<?, ?> truongJoin = sinhVienJoin.join("nhaTruong");

            // Trạng thái = CHAP_NHAN
            predicates.add(criteriaBuilder.equal(root.get("trangThai"), TrangThaiThucTap.CHAP_NHAN));

            // Lọc theo mã doanh nghiệp
            if (maDoanhNghiep != null && !maDoanhNghiep.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("doanhNghiep").get("maDoanhNghiep"), maDoanhNghiep));
            }

            // Lọc theo từ khóa tên sinh viên
            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(sinhVienJoin.get("hoVaTen")),
                        "%" + keyword.toLowerCase() + "%"
                ));
            }

            // Lọc theo vị trí thực tập
            if (viTriThucTap != null && !viTriThucTap.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("viTriThucTap")),
                        "%" + viTriThucTap.toLowerCase() + "%"
                ));
            }

            // Lọc theo mã nhà trường
            if (maNhaTruong != null && !maNhaTruong.isEmpty()) {
                predicates.add(criteriaBuilder.equal(truongJoin.get("maNhaTruong"), maNhaTruong));
            }

            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
    }
}
