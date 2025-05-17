package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.KyNangDto;
import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.repository.DanhMucRepository;
import com.graduate.recruitment.repository.KyNangRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class KyNangService {
    private KyNangRepository kyNangRepository;
    private DanhMucRepository danhMucRepository;
    public Page<KyNang> getAllKyNang(Integer page,Integer limit){
        Pageable pageable = PageRequest.of(page,limit, Sort.by(Sort.Direction.DESC, "taoVaoLuc"));
        return kyNangRepository.findAll(pageable);
    }

    public KyNang themKyNang(KyNangDto kyNangDto){
        try {
            DanhMuc danhMuc = danhMucRepository.findById(kyNangDto.getMaDanhMuc())
                    .orElseThrow(()->new EntityNotFoundException("Không tồn tại danh mục"));
            KyNang kyNang = new KyNang();
            long size = kyNangRepository.count();
            kyNang.setMaKyNang(String.format("KN%03d",size+1));
            kyNang.setTenKyNang(kyNangDto.getTenKyNang());
            kyNang.setDanhMuc(danhMuc);
            kyNang.setTaoVaoLuc(LocalDateTime.now());
            kyNang.setCapNhatVaoLuc(LocalDateTime.now());
            kyNang = kyNangRepository.save(kyNang);
            return kyNang;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
