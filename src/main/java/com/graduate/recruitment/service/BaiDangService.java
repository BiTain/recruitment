package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.mapper.BaiDangMapper;
import com.graduate.recruitment.repository.BaiDangRepository;
import com.graduate.recruitment.repository.DanhMucRepository;
import com.graduate.recruitment.repository.KyNangRepository;
import com.graduate.recruitment.specification.BaiDangSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class BaiDangService {
    private BaiDangRepository baiDangRepository;
    public Page<BaiDangDto> getAll(Integer page, Integer limit,String kyNang){
        Specification<BaiDang> spec = Specification.where((BaiDangSpecification.hasKyNang(kyNang))
                .and(BaiDangSpecification.hasTrangThai("CON_HAN")));
        Sort sort = Sort.by(Sort.Direction.DESC,"taoVaoLuc");
        Pageable pageable = PageRequest.of(page,limit,sort);
        Page<BaiDang> baiDangs = baiDangRepository.findAll(spec,pageable);
        return baiDangs.map(BaiDangMapper::toDto);
    }

    public BaiDangDto getByMaBaiDang(String maBaiDang){
        BaiDang baiDang = baiDangRepository.findById(maBaiDang).orElseThrow();
        return BaiDangMapper.toDto(baiDang);
    }
}
