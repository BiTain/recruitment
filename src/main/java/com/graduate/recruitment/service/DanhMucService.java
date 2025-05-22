package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.DanhMuc;
import com.graduate.recruitment.repository.DanhMucRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DanhMucService {
    private DanhMucRepository danhMucRepository;
    public Page<DanhMuc> getAllDanhMuc(Integer page,Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        return danhMucRepository.findAll(pageable);
    }

    public List<DanhMuc> getAllDanhMuc(){
        return danhMucRepository.findAll();
    }
}
