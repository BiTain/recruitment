package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.mapper.BaiDangMapper;
import com.graduate.recruitment.repository.BaiDangRepository;
import com.graduate.recruitment.repository.DanhMucRepository;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.KyNangRepository;
import com.graduate.recruitment.specification.BaiDangSpecification;
import jakarta.persistence.EntityNotFoundException;
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
    private DoanhNghiepRepository doanhNghiepRepository;
    public Page<BaiDangDto> getAll(Integer page, Integer limit,String kyNang, String search){
        Specification<BaiDang> spec = Specification.where((BaiDangSpecification.hasKyNang(kyNang))
                        .and(BaiDangSpecification.searchByKey(search))
                .and(BaiDangSpecification.hasTrangThai("CON_HAN")));
        Sort sort = Sort.by(Sort.Direction.DESC,"taoVaoLuc");
        Pageable pageable = PageRequest.of(page,limit,sort);
        Page<BaiDang> baiDangs = baiDangRepository.findAll(spec,pageable);
        return baiDangs.map(BaiDangMapper::toDto);
    }

    public Page<BaiDang> getAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        return baiDangRepository.findAll(pageable);
    }

    public BaiDangDto getByMaBaiDang(String maBaiDang){
        BaiDang baiDang = baiDangRepository.findById(maBaiDang).orElseThrow();
        return BaiDangMapper.toDto(baiDang);
    }

    public BaiDang getByMaBaiDang1(String maBaiDang){
        return baiDangRepository.findById(maBaiDang).orElseThrow();

    }

    public Page<BaiDang> getAllBaiDangByMaDoanhNghiep(Integer page, Integer limit,String maDoanhNghiep){
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(maDoanhNghiep)
                .orElseThrow(()-> new EntityNotFoundException("Không tìm thấy doanh nghiệp có mã: "+maDoanhNghiep));
        Pageable pageable = PageRequest.of(page,limit);
        return baiDangRepository.findByDoanhNghiep(doanhNghiep,pageable);
    }
}
