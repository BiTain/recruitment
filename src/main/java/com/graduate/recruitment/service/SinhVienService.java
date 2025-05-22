package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.SinhVienDto;
import com.graduate.recruitment.entity.NhaTruong;
import com.graduate.recruitment.entity.SinhVien;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.entity.enums.GioiTinh;
import com.graduate.recruitment.entity.enums.TrangThaiSinhVien;
import com.graduate.recruitment.mapper.SinhVienMapper;
import com.graduate.recruitment.repository.NhaTruongRepository;
import com.graduate.recruitment.repository.SinhVienRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SinhVienService {
    private SinhVienRepository sinhVienRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private NhaTruongRepository nhaTruongRepository;

    public SinhVienDto getByMaSinhVien(String maSinhVien){
        SinhVien sinhVien = sinhVienRepository.findById(maSinhVien)
                .orElseThrow(()->new EntityNotFoundException("Sinh viên không tồn tại"));
        return SinhVienMapper.toDto(sinhVien);
    }

    public SinhVien getByMaTaiKhoan(String maTaiKhoan){
        TaiKhoan taiKhoan = taiKhoanRepository.findById(maTaiKhoan)
                .orElseThrow(()->new EntityNotFoundException("Không tìm thấy tài khoản"));

        return sinhVienRepository.findByTaiKhoan(taiKhoan);
    }

    public Page<SinhVien> getAllSinhVien(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        return sinhVienRepository.findAll(pageable);
    }

    public SinhVien saveSinhVien(String maTaiKhoan,SinhVienDto sinhVienDto){
        try {
            TaiKhoan taiKhoan = taiKhoanRepository.findById(maTaiKhoan)
                    .orElseThrow(()-> new EntityNotFoundException("Không tìm thấy tài khoản"));
            NhaTruong nhaTruong = nhaTruongRepository.findById(sinhVienDto.getMaNhaTruong())
                    .orElseThrow(()->new EntityNotFoundException("Không tìm thấy nhà trường"));
            SinhVien sinhVien = sinhVienRepository.findByTaiKhoan(taiKhoan);
            if(sinhVien != null){
                sinhVienRepository.delete(sinhVien);
            }
            sinhVien = new SinhVien();
            sinhVien.setTaiKhoan(taiKhoan);
            sinhVien.setNhaTruong(nhaTruong);
            sinhVien.setMaSinhVien(sinhVienDto.getMaSinhVien());
            sinhVien.setHoVaTen(sinhVienDto.getHoVaTen());
            sinhVien.setNgaySinh(sinhVienDto.getNgaySinh());
            sinhVien.setSoDienThoai(sinhVienDto.getSoDienThoai());
            sinhVien.setGioiTinh(GioiTinh.valueOf(sinhVienDto.getGioiTinh()));
            sinhVien.setCccd(sinhVienDto.getCccd());
            sinhVien.setDiaChi(sinhVienDto.getDiaChi());
            sinhVien.setKhoa(sinhVienDto.getKhoa());
            sinhVien.setLop(sinhVienDto.getLop());
            sinhVien.setChuyenNganh(sinhVienDto.getChuyenNganh());
            sinhVien.setTaoVaoLuc(LocalDateTime.now());
            sinhVien.setCapNhatVaoLuc(LocalDateTime.now());
            sinhVien = sinhVienRepository.save(sinhVien);
            return sinhVien;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
