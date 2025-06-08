package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.KyNangDto;
import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.entity.KyNangBaiDang;
import com.graduate.recruitment.mapper.KyNangMapper;
import com.graduate.recruitment.repository.DanhMucRepository;
import com.graduate.recruitment.repository.KyNangBaiDangRepository;
import com.graduate.recruitment.repository.KyNangRepository;
import com.graduate.recruitment.specification.KyNangSpecification;
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

@Service
@AllArgsConstructor
public class KyNangService {
    private KyNangRepository kyNangRepository;
    private DanhMucRepository danhMucRepository;
    private KyNangBaiDangRepository kyNangBaiDangRepository;

    public Page<KyNang> getAllKyNang(Integer page, Integer limit,String keyword, String maDanhMuc, String sapXepBy) {
        Pageable pageable = PageRequest.of(page, limit, buildSort(sapXepBy));
        Specification<KyNang> spec = KyNangSpecification.searchByTenKyNangAndMaDanhMuc(keyword, maDanhMuc);
        return kyNangRepository.findAll(spec,pageable);
    }

    private Sort buildSort(String sapXepBy) {
        if (sapXepBy == null || sapXepBy.isEmpty()) {
            return Sort.by(Sort.Direction.DESC, "taoVaoLuc");
        }
        return switch (sapXepBy) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "tenKyNang");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "tenKyNang");
            case "dateNewest" -> Sort.by(Sort.Direction.DESC, "taoVaoLuc");
            case "dateOldest" -> Sort.by(Sort.Direction.ASC, "taoVaoLuc");
            default -> Sort.unsorted();
        };
    }

    public Page<KyNang> getAllKyNang(Integer page, Integer limit, String keyword, String maDanhMuc) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "taoVaoLuc"));
        Specification<KyNang> spec = Specification.where(null);

        if (keyword != null && !keyword.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("tenKyNang")), "%" + keyword.toLowerCase() + "%")
            );
        }
        if (maDanhMuc != null && !maDanhMuc.isBlank() && !maDanhMuc.equals("tat-ca")) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("danhMuc").get("maDanhMuc"), maDanhMuc)
            );
        }

        return kyNangRepository.findAll(spec, pageable);
    }

    public KyNang themKyNang(KyNangDto kyNangDto) {
        try {
            DanhMuc danhMuc = danhMucRepository.findById(kyNangDto.getMaDanhMuc())
                    .orElseThrow(() -> new EntityNotFoundException("Không tồn tại danh mục"));
            KyNang kyNang = new KyNang();
            long size = kyNangRepository.count();
            kyNang.setMaKyNang(String.format("KN%03d", size + 1));
            kyNang.setTenKyNang(kyNangDto.getTenKyNang());
            kyNang.setDanhMuc(danhMuc);
            kyNang.setTaoVaoLuc(LocalDateTime.now());
            kyNang.setCapNhatVaoLuc(LocalDateTime.now());
            kyNang = kyNangRepository.save(kyNang);
            return kyNang;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean suaKyNang(KyNangDto kyNangDto) {
        KyNang kyNang = kyNangRepository.findById(kyNangDto.getMaKyNang()).get();
        kyNang.setTenKyNang(kyNangDto.getTenKyNang());
        kyNang.setDanhMuc(danhMucRepository.findById(kyNangDto.getMaDanhMuc()).get());
        kyNangRepository.save(kyNang);
        return true;
    }

    public boolean xoaKyNang(String maKyNang) {
        KyNang kyNang = kyNangRepository.findById(maKyNang).get();
        kyNangBaiDangRepository.deleteAll(kyNang.getKyNangBaiDangs());
        kyNangRepository.delete(kyNang);
        return true;
    }
}
