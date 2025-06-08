package com.graduate.recruitment.specification;

import com.graduate.recruitment.entity.KyNang;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class KyNangSpecification {

    public static Specification<KyNang> searchByTenKyNangAndMaDanhMuc(String keyword, String maDanhMuc) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("tenKyNang")),
                                "%" + keyword.toLowerCase() + "%"
                        )
                );
            }

            if (maDanhMuc != null && !maDanhMuc.isEmpty()) {
                predicates.add(
                        criteriaBuilder.equal(root.get("danhMuc").get("maDanhMuc"), maDanhMuc)
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

