package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.repository.NhaTruongRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NhaTruongService {
    private NhaTruongRepository nhaTruongRepository;

    public NhaTruong getNhaTruong(String maNhaTruong){
        return nhaTruongRepository.findById(maNhaTruong)
                .orElseThrow(()->new EntityNotFoundException("Không tìm thấy nhà trường với ID: " + maNhaTruong));
    }
}
