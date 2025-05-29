package com.graduate.recruitment.specification;

import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SinhVienBaiDangSpecification {

    public static Specification<SinhVienBaiDang> filter(
            String maDoanhNghiep,
            KetQua ketQua,
            String maNhaTruong,
            String maBaiDang,
            String keyword
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("baiDang").get("doanhNghiep").get("maDoanhNghiep"), maDoanhNghiep));
            predicates.add(cb.equal(root.get("ketQua"), ketQua));

            if (maNhaTruong != null && !maNhaTruong.isEmpty()) {
                predicates.add(cb.equal(root.get("sinhVien").get("nhaTruong").get("maNhaTruong"), maNhaTruong));
            }

            if (maBaiDang != null && !maBaiDang.isEmpty()) {
                predicates.add(cb.equal(root.get("baiDang").get("maBaiDang"), maBaiDang));
            }

            if (keyword != null && !keyword.isEmpty()) {
                Predicate hoTen = cb.like(cb.lower(root.get("sinhVien").get("hoVaTen")), "%" + keyword.toLowerCase() + "%");
                predicates.add(hoTen);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
