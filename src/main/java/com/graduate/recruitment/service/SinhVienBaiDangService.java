package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.SinhVienBaiDangDto;
import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.mapper.SinhVienBaiDangMapper;
import com.graduate.recruitment.repository.BaiDangRepository;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class SinhVienBaiDangService {

    private SinhVienBaiDangRepository sinhVienBaiDangRepository;
    private SinhVienRepository sinhVienRepository;
    private BaiDangRepository baiDangRepository;
    private FileService fileService;

    public List<SinhVienBaiDangDto> getBaiDangApplied(String maSinhVien){
        List<SinhVienBaiDang> sinhVienBaiDangs = sinhVienBaiDangRepository.findBaiDangsByMaSinhVien(maSinhVien);
        return sinhVienBaiDangs.stream().map(SinhVienBaiDangMapper::toDto).toList();
    }

    public SinhVienBaiDangDto applyBaiDang(SinhVienBaiDangDto sinhVienBaiDangDto){
        try {
            SinhVien sinhVien = sinhVienRepository.findById(sinhVienBaiDangDto.getMaSinhVien()).orElseThrow();
            BaiDang baiDang = baiDangRepository.findById(sinhVienBaiDangDto.getMaBaiDang()).orElseThrow();
            SinhVienBaiDang sinhVienBaiDang = SinhVienBaiDangMapper.toEntity(sinhVienBaiDangDto);
            long size = sinhVienBaiDangRepository.count();
            sinhVienBaiDang.setMaSVBD(String.format("SVBD%03d",size+1));
            sinhVienBaiDang.setSinhVien(sinhVien);
            sinhVienBaiDang.setBaiDang(baiDang);
            sinhVienBaiDang.setSoYeuLyLich(fileService.store(sinhVienBaiDangDto.getFileCV()));
            sinhVienBaiDang.setKetQua(KetQua.DANG_CHO);
            sinhVienBaiDang = sinhVienBaiDangRepository.save(sinhVienBaiDang);
            return SinhVienBaiDangMapper.toDto(sinhVienBaiDang);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


}
