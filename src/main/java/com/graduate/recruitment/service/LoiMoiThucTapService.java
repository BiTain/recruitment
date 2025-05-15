package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.LoiMoiThucTapDto;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.LoiMoiThucTap;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.SinhVienBaiDang;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.entity.enums.TrangThaiThucTap;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.LoiMoiThucTapRepository;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class LoiMoiThucTapService {
    private LoiMoiThucTapRepository loiMoiThucTapRepository;
    private SinhVienRepository sinhVienRepository;
    private DoanhNghiepRepository doanhNghiepRepository;
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;

    public Map<String, List<LoiMoiThucTap>> getAllLoiMoiThucTapByTrangThai(String maSinhVien){
        SinhVien sinhVien = sinhVienRepository.findById(maSinhVien)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với mã: " + maSinhVien));

        List<LoiMoiThucTap> loiMoiThucTapList = loiMoiThucTapRepository.findAllBySinhVien(sinhVien);

        LocalDateTime now = LocalDateTime.now();

        Map<String, List<LoiMoiThucTap>> result = new HashMap<>();

        result.put("dang-cho", loiMoiThucTapList.stream()
                .filter(lmtt -> lmtt.getTrangThai() == TrangThaiThucTap.DANG_CHO
                && lmtt.getHanXacNhan().isAfter(now))
                .sorted(Comparator.comparing(LoiMoiThucTap::getCapNhatVaoLuc).reversed())
                .collect(Collectors.toList()));

        result.put("chap-nhan", loiMoiThucTapList.stream()
                .filter(lmtt -> lmtt.getTrangThai() == TrangThaiThucTap.CHAP_NHAN)
                .sorted(Comparator.comparing(LoiMoiThucTap::getCapNhatVaoLuc).reversed())
                .collect(Collectors.toList()));

        result.put("tu-choi", loiMoiThucTapList.stream()
                .filter(lmtt -> lmtt.getTrangThai() == TrangThaiThucTap.TU_CHOI
                || (lmtt.getTrangThai() == TrangThaiThucTap.DANG_CHO && lmtt.getHanXacNhan().isBefore(now)))
                .sorted(Comparator.comparing(LoiMoiThucTap::getCapNhatVaoLuc).reversed())
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
        loiMoiThucTap.setCapNhatVaoLuc(LocalDateTime.now());
        return loiMoiThucTapRepository.save(loiMoiThucTap);
    }

    public LoiMoiThucTap taoLMTT(LoiMoiThucTapDto loiMoiThucTapDto){
        try {
            SinhVienBaiDang hoSo = sinhVienBaiDangRepository.findById(loiMoiThucTapDto.getMaHoSo())
                    .orElseThrow(()->new EntityNotFoundException("Không tìm thấy hồ sơ"));
            SinhVien sinhVien = sinhVienRepository.findById(loiMoiThucTapDto.getMaSinhVien())
                    .orElseThrow(()->new EntityNotFoundException("Không tìm thấy sinh viên"));
            DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(loiMoiThucTapDto.getMaDoanhNghiep())
                    .orElseThrow(()->new EntityNotFoundException("Không tìm thấy doanh nghiệp"));
            LoiMoiThucTap loiMoiThucTap = new LoiMoiThucTap();
            long size = loiMoiThucTapRepository.count();
            loiMoiThucTap.setMaLMTT(String.format("LMTT%03d",size+1));
            loiMoiThucTap.setSinhVien(sinhVien);
            loiMoiThucTap.setDoanhNghiep(doanhNghiep);
            loiMoiThucTap.setLoaiThucTap(hoSo.getBaiDang().getLoai());
            loiMoiThucTap.setViTriThucTap(hoSo.getBaiDang().getTieuDe());
            loiMoiThucTap.setDiaChi(hoSo.getBaiDang().getDiaChi());
            loiMoiThucTap.setQuyenLoi(hoSo.getBaiDang().getQuyenLoi());
            loiMoiThucTap.setTuNgay(loiMoiThucTapDto.getTuNgay());
            loiMoiThucTap.setDenNgay(loiMoiThucTapDto.getDenNgay());
            loiMoiThucTap.setHanXacNhan(loiMoiThucTapDto.getHanXacNhan());
            loiMoiThucTap.setTrangThai(TrangThaiThucTap.DANG_CHO);
            loiMoiThucTap.setTaoVaoLuc(LocalDateTime.now());
            loiMoiThucTap.setCapNhatVaoLuc(LocalDateTime.now());
            hoSo.setKetQua(KetQua.THONG_QUA);
            hoSo.setCapNhatVaoLuc(LocalDateTime.now());
            sinhVienBaiDangRepository.save(hoSo);
            loiMoiThucTap = loiMoiThucTapRepository.save(loiMoiThucTap);
            return loiMoiThucTap;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
