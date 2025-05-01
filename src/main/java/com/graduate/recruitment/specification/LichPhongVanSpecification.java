package com.graduate.recruitment.specification;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.LichPhongVan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class LichPhongVanSpecification {
    public static Specification<LichPhongVan> hasTrangThai(String trangThai) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(trangThai)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("trangThai"), trangThai);
        };
    }
}
