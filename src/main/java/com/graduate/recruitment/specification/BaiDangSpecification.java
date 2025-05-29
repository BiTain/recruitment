package com.graduate.recruitment.specification;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.enums.Loai;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
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

    public static Specification<BaiDang> searchByKey(String key){
        return (root, query, cb)->{
            if (!StringUtils.hasText(key)) {
                return cb.conjunction();
            }

            String pattern = "%" + key.toLowerCase().trim() + "%";
            Join<BaiDang, ?> doanhNghiep = root.join("doanhNghiep", JoinType.LEFT);
            Join<BaiDang, ?> danhMuc = root.join("danhMuc", JoinType.LEFT);
            Join<BaiDang, ?> kyNang = root.join("kyNangBaiDangs", JoinType.LEFT).join("kyNang", JoinType.LEFT);
            return cb.or(
                    cb.like(cb.lower(doanhNghiep.get("tenDoanhNghiep")), pattern),
                    cb.like(cb.lower(danhMuc.get("tenDanhMuc")), pattern),
                    cb.like(cb.lower(kyNang.get("tenKyNang")), pattern)
            );
        };
    }

    public static Specification<BaiDang> filterBy(
            String maDoanhNghiep,
            String keyword,
            String maDanhMuc,
            String trangThai,
            String loai
    ) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            // Mã doanh nghiệp là bắt buộc
            if (maDoanhNghiep != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("doanhNghiep").get("maDoanhNghiep"), maDoanhNghiep));
            }

            if (keyword != null && !keyword.trim().isEmpty()) {
                String likePattern = "%" + keyword.trim().toLowerCase() + "%";
                predicate = cb.and(predicate,
                        cb.or(
                                cb.like(cb.lower(root.get("tieuDe")), likePattern)
                        ));
            }

            if (maDanhMuc != null && !maDanhMuc.isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("danhMuc").get("maDanhMuc"), maDanhMuc));
            }

            if (trangThai != null && !trangThai.isEmpty()) {
                try {
                    TrangThaiBaiDang enumValue = TrangThaiBaiDang.valueOf(trangThai);
                    predicate = cb.and(predicate,
                            cb.equal(root.get("trangThai"), enumValue));
                } catch (IllegalArgumentException e) {
                }
            }

            if (loai != null && !loai.isEmpty()) {
                try {
                    Loai loaiBaiDang = Loai.valueOf(loai);
                    predicate = cb.and(predicate, cb.equal(root.get("loai"), loaiBaiDang));
                } catch (IllegalArgumentException ignored) {
                }
            }

            return predicate;
        };
    }
}
