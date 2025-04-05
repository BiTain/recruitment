package com.graduate.recruitment.specification;

import com.graduate.recruitment.entity.BaiDang;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BaiDangSpecification {
    public static Specification<BaiDang> hasDanhMuc(String maDanhMuc) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(maDanhMuc)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("danhMuc").get("maDanhMuc"), maDanhMuc);
        };
    }

    public static Specification<BaiDang> hasKyNang(String maKyNang) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(maKyNang)) {
                return criteriaBuilder.conjunction();
            }

            // Use distinct to avoid duplicates when joining
            query.distinct(true);

            Join<Object, Object> kyNangBaiDangJoin = root.join("kyNangBaiDangs", JoinType.INNER);
            Join<Object, Object> kyNangJoin = kyNangBaiDangJoin.join("kyNang", JoinType.INNER);

            return criteriaBuilder.equal(kyNangJoin.get("maKyNang"), maKyNang);
        };
    }

    public static Specification<BaiDang> hasTrangThai(String trangThai) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(trangThai)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("trangThai"), trangThai);
        };
    }
}
