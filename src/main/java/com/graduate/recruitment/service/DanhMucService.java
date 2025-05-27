package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.repository.DanhMucRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DanhMucService {
    private DanhMucRepository danhMucRepository;
    public Page<DanhMuc> getAllDanhMuc(Integer page,Integer limit, String keyword){
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "taoVaoLuc"));

        Specification<DanhMuc> spec = Specification.where(null);

        if (keyword != null && !keyword.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("tenDanhMuc")), "%" + keyword.toLowerCase() + "%")
            );
        }
        return danhMucRepository.findAll(spec, pageable);
    }

    public List<DanhMuc> getAllDanhMuc(){
        return danhMucRepository.findAll();
    }
}
