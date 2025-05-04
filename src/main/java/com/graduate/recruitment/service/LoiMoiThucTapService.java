package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.LoiMoiThucTap;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.entity.enums.TrangThaiThucTap;
import com.graduate.recruitment.repository.LoiMoiThucTapRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class LoiMoiThucTapService {
    private LoiMoiThucTapRepository loiMoiThucTapRepository;
    private SinhVienRepository sinhVienRepository;

    public Map<String, List<LoiMoiThucTap>> getAllLoiMoiThucTapByTrangThai(String maSinhVien){
        SinhVien sinhVien = sinhVienRepository.findById(maSinhVien)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với mã: " + maSinhVien));

        List<LoiMoiThucTap> loiMoiThucTapList = loiMoiThucTapRepository.findAllBySinhVien(sinhVien);

        LocalDateTime now = LocalDateTime.now();

        Map<String, List<LoiMoiThucTap>> result = new HashMap<>();

        result.put("dang-cho", loiMoiThucTapList.stream()
                .filter(lmtt -> lmtt.getTrangThai() == TrangThaiThucTap.DANG_CHO
                && lmtt.getHanXacNhan().isAfter(now))
                .collect(Collectors.toList()));

        result.put("chap-nhan", loiMoiThucTapList.stream()
                .filter(lmtt -> lmtt.getTrangThai() == TrangThaiThucTap.CHAP_NHAN)
                .collect(Collectors.toList()));

        result.put("tu-choi", loiMoiThucTapList.stream()
                .filter(lmtt -> lmtt.getTrangThai() == TrangThaiThucTap.TU_CHOI
                || (lmtt.getTrangThai() == TrangThaiThucTap.DANG_CHO && lmtt.getHanXacNhan().isBefore(now)))
                .collect(Collectors.toList()));

        return result;
    }

    @Transactional
    public LoiMoiThucTap chapNhanOrTuChoi(String maLMTT,String trangThai){
        LoiMoiThucTap loiMoiThucTap = loiMoiThucTapRepository.findById(maLMTT)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy lời mời thực tập với ID: " + maLMTT));

        if(trangThai.equals("chap-nhan")){
            loiMoiThucTap.setTrangThai(TrangThaiThucTap.CHAP_NHAN);
        }
        if (trangThai.equals("tu-choi")){
            loiMoiThucTap.setTrangThai(TrangThaiThucTap.TU_CHOI);
        }
        return loiMoiThucTapRepository.save(loiMoiThucTap);
    }

}
