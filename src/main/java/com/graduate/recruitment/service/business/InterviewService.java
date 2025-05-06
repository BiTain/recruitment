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
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
            lichPhongVan.setViTriPhongVan(lichPhongVan.getViTriPhongVan());
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

    public List<LichPhongVan> getLichPhongVanTrongNgay(String maDoanhNghiep){
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(maDoanhNghiep).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy doanh nghiệp có mã: "+maDoanhNghiep));
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        return doanhNghiep.getLichPhongVans().stream()
                .filter(lpv -> !lpv.getNgayPhongVan().isBefore(startOfDay)
                        && !lpv.getNgayPhongVan().isAfter(endOfDay)
                        && lpv.getTrangThai().name().equals("DONG_Y"))
                .collect(Collectors.toList());
    }

    public Page<LichPhongVan> getAllLichPhongVan(String maDoanhNghiep, Integer page, Integer limit){
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(maDoanhNghiep).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy doanh nghiệp có mã: "+maDoanhNghiep));
        Pageable pageable = PageRequest.of(page,limit);
        return lichPhongVanRepository.findAllByDoanhNghiep(doanhNghiep,pageable);
    }
}
