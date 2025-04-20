package com.graduate.recruitment.service.business;

import com.graduate.recruitment.dto.LichPhongVanDto;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.HinhThucPhongVan;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.LichPhongVanRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class InterviewService {
    private LichPhongVanRepository lichPhongVanRepository;
    private SinhVienRepository sinhVienRepository;
    private DoanhNghiepRepository doanhNghiepRepository;

    public LichPhongVan createLichPhongVan(LichPhongVanDto lichPhongVanDto){
        try {
            SinhVien sinhVien = sinhVienRepository.findById(lichPhongVanDto.getMaSinhVien()).orElseThrow();
            DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(lichPhongVanDto.getMaDoanhNghiep()).orElseThrow();
            LichPhongVan lichPhongVan = new LichPhongVan();
            long size = lichPhongVanRepository.count();
            lichPhongVan.setMaLichPhongVan(String.format("LPV%03d",size+1));
            lichPhongVan.setSinhVien(sinhVien);
            lichPhongVan.setDoanhNghiep(doanhNghiep);
            lichPhongVan.setNgayPhongVan(lichPhongVanDto.getNgayPhongVan());
            lichPhongVan.setHanXacNhan(lichPhongVanDto.getHanXacNhan());
            lichPhongVan.setHinhThucPhongVan(HinhThucPhongVan.valueOf(lichPhongVanDto.getHinhThucPhongVan()));
            lichPhongVan.setDiaDiem(lichPhongVanDto.getDiaDiem());
            lichPhongVan.setTrangThai(TrangThaiPhongVan.DANG_CHO);
            lichPhongVan.setTaoVaoLuc(LocalDateTime.now());
            lichPhongVan.setCapNhatVaoLuc(LocalDateTime.now());
            lichPhongVan = lichPhongVanRepository.save(lichPhongVan);
            return lichPhongVan;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
