package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.repository.NhaTruongRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NhaTruongService {
    private NhaTruongRepository nhaTruongRepository;

    public NhaTruong getNhaTruong(String maNhaTruong){
        return nhaTruongRepository.findById(maNhaTruong)
                .orElseThrow(()->new EntityNotFoundException("Không tìm thấy nhà trường với ID: " + maNhaTruong));
    }

    public Page<NhaTruong> getAllNhaTruong(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        return nhaTruongRepository.findAll(pageable);
    }
}
