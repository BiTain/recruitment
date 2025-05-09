package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.KyNang;
import com.graduate.recruitment.repository.KyNangRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KyNangService {
    private KyNangRepository kyNangRepository;
    public Page<KyNang> getAllKyNang(Integer page,Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        return kyNangRepository.findAll(pageable);
    }
}
