package com.graduate.recruitment.specification;

import com.graduate.recruitment.entity.DoanhNghiep;
import org.springframework.data.jpa.domain.Specification;

public class DoanhNghiepSpecification {

    public static Specification<DoanhNghiep> buildSpecification(String keyword, String huyen) {
        return (root, query, builder) -> {
            Specification<DoanhNghiep> spec = Specification.where(null);

            if (keyword != null && !keyword.trim().isEmpty()) {
                String keywordPattern = "%" + keyword.trim().toLowerCase() + "%";
                spec = spec.and((r, q, b) -> b.like(b.lower(r.get("tenDoanhNghiep")), keywordPattern));
            }

            if (huyen != null && !huyen.trim().isEmpty() && !huyen.equalsIgnoreCase("Tất cả quận/huyện")) {
                String huyenTrimmed = huyen.trim();
                String patternQuan = "%Quận " + huyenTrimmed + "%";
                String patternHuyen = "%Huyện " + huyenTrimmed + "%";

                spec = spec.and((r, q, b) ->
                        b.or(
                                b.like(r.get("diaChi"), patternQuan),
                                b.like(r.get("diaChi"), patternHuyen)
                        )
                );
            }

            return spec.toPredicate(root, query, builder);
        };
    }
}

