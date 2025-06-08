package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.mapper.DoanhNghiepMapper;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.specification.DoanhNghiepSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoanhnghiepService {
    private DoanhNghiepRepository doanhNghiepRepository;

    public DoanhNghiepDto getDoanhNghiepById(String maDoanhNghiep) {
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(maDoanhNghiep).orElseThrow();
        return DoanhNghiepMapper.toDto(doanhNghiep);
    }

    public Page<DoanhNghiepDto> getAll(Integer page, Integer limit, String keyword, String huyen, String sapXepBy) {
        Pageable pageable = PageRequest.of(page, limit, buildSort(sapXepBy));
        Specification<DoanhNghiep> spec = DoanhNghiepSpecification.buildSpecification(keyword, huyen);
        Page<DoanhNghiep> doanhNghieps = doanhNghiepRepository.findAll(spec, pageable);
        return doanhNghieps.map(DoanhNghiepMapper::toDto);
    }

    private Sort buildSort(String sapXepBy) {
        if (sapXepBy == null || sapXepBy.isEmpty()) {
            return Sort.by(Sort.Direction.DESC, "taoVaoLuc");
        }
        return switch (sapXepBy) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "tenDoanhNghiep");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "tenDoanhNghiep");
            case "dateNewest" -> Sort.by(Sort.Direction.DESC, "taoVaoLuc");
            case "dateOldest" -> Sort.by(Sort.Direction.ASC, "taoVaoLuc");
            default -> Sort.unsorted();
        };

    }
}
