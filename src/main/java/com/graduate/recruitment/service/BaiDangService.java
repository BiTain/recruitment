package com.graduate.recruitment.service;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.entity.*;
import com.graduate.recruitment.entity.enums.Loai;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
import com.graduate.recruitment.mapper.BaiDangMapper;
import com.graduate.recruitment.repository.*;
import com.graduate.recruitment.specification.BaiDangSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class BaiDangService {
    private BaiDangRepository baiDangRepository;
    private DoanhNghiepRepository doanhNghiepRepository;
    private DanhMucRepository danhMucRepository;
    private KyNangRepository kyNangRepository;
    private KyNangBaiDangRepository kyNangBaiDangRepository;

    public Page<BaiDangDto> getAllSinhVienBaiDang(Integer page, Integer limit, String kyNang, String search, String loai, String khuVuc) {
        Specification<BaiDang> spec = Specification
                .where(
                        (BaiDangSpecification.hasKyNang(kyNang)
                        )
                                .and(BaiDangSpecification.searchByKey(search)
                                )
                                .and(BaiDangSpecification.hasKhuVuc(khuVuc))
                                .and(BaiDangSpecification.hasLoai(loai))
                                .and(BaiDangSpecification.hasTrangThai("CON_HAN")));
        Sort sort = Sort.by(Sort.Direction.DESC, "taoVaoLuc");
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<BaiDang> baiDangs = baiDangRepository.findAll(spec, pageable);
        return baiDangs.map(BaiDangMapper::toDto);
    }

    public Page<BaiDang> getAll(Integer page, Integer limit, String keyword, String maDoanhNghiep, String trangThai, String loai) {
        Pageable pageable = PageRequest.of(page, limit);
        Specification<BaiDang> spec = BaiDangSpecification.filterBaiDangForAdmin(keyword, maDoanhNghiep, trangThai, loai);
        return baiDangRepository.findAll(spec, pageable);
    }

    public BaiDangDto getByMaBaiDang(String maBaiDang) {
        BaiDang baiDang = baiDangRepository.findById(maBaiDang)
                .orElseThrow(() -> new EntityNotFoundException("Bài đăng không tồn tại!"));
        return BaiDangMapper.toDto(baiDang);
    }

    public BaiDang getByMaBaiDang1(String maBaiDang) {
        return baiDangRepository.findById(maBaiDang)
                .orElseThrow(() -> new EntityNotFoundException("Bài đăng không tồn tại!"));

    }

    public Page<BaiDang> getAllBaiDangByMaDoanhNghiep(Integer page, Integer limit, String maDoanhNghiep,
                                                      String keyword, String maDanhMuc, String trangThai, String loai) {
        Pageable pageable = PageRequest.of(page, limit,Sort.by(Sort.Direction.DESC, "capNhatVaoLuc"));
        return baiDangRepository.findAll(
                BaiDangSpecification.filterBy(maDoanhNghiep, keyword, maDanhMuc, trangThai, loai),
                pageable
        );
    }

    public BaiDang taoBaiDang(BaiDangDto baiDangDto) {
        try {
            DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(baiDangDto.getMaDoanhNghiep())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy doanh nghiệp"));
            DanhMuc danhMuc = danhMucRepository.findById(baiDangDto.getMaDanhMuc())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy danh mục"));
            BaiDang baiDang = new BaiDang();
            long size = baiDangRepository.count();
            baiDang.setMaBaiDang(String.format("BD%03d", size + 1));
            baiDang.setDoanhNghiep(doanhNghiep);
            baiDang.setDanhMuc(danhMuc);
            baiDang.setTieuDe(baiDangDto.getTieuDe());
            baiDang.setDiaChi(String.format("%s, %s, %s", baiDangDto.getDiaChi(), baiDangDto.getPhuong(), baiDangDto.getHuyen()));
            baiDang.setYeuCau(baiDangDto.getYeuCauString());
            baiDang.setMoTa(baiDangDto.getMoTa());
            baiDang.setDenHan(baiDangDto.getDenHan());
            baiDang.setQuyenLoi(baiDangDto.getQuyenLoiString());
            baiDang.setLoai(Loai.valueOf(baiDangDto.getLoai()));
            baiDang.setTrangThai(TrangThaiBaiDang.CON_HAN);
            baiDang.setTaoVaoLuc(LocalDateTime.now());
            baiDang.setCapNhatVaoLuc(LocalDateTime.now());
            baiDang = baiDangRepository.save(baiDang);
            if (baiDangDto.getMaKyNangs() != null && !baiDangDto.getMaKyNangs().isEmpty()) {

                for (String maKyNang : baiDangDto.getMaKyNangs()) {
                    KyNang kyNang = kyNangRepository.findById(maKyNang)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy kỹ năng với mã: " + maKyNang));

                    KyNangBaiDang kyNangBaiDang = new KyNangBaiDang();
                    kyNangBaiDang.setBaiDang(baiDang);
                    kyNangBaiDang.setKyNang(kyNang);
                    kyNangBaiDang.setTaoVaoLuc(LocalDateTime.now());
                    kyNangBaiDang.setCapNhatVaoLuc(LocalDateTime.now());

                    kyNangBaiDangRepository.save(kyNangBaiDang);
                }
            }
            return baiDang;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public BaiDang updateBaiDang(BaiDangDto baiDangDto) {
        try {
            BaiDang baiDang = baiDangRepository.findById(baiDangDto.getMaBaiDang()).
                    orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bài đăng"));
            baiDang.setTieuDe(baiDangDto.getTieuDe());
            baiDang.setDiaChi(String.format("%s, %s, %s", baiDangDto.getDiaChi(), baiDangDto.getPhuong(), baiDangDto.getHuyen()));
            baiDang.setYeuCau(baiDangDto.getYeuCauString());
            baiDang.setMoTa(baiDangDto.getMoTa());
            baiDang.setDenHan(baiDangDto.getDenHanUpdate().atTime(LocalTime.of(23, 59, 59)));
            baiDang.setQuyenLoi(baiDangDto.getQuyenLoiString());
            baiDang.setLoai(Loai.valueOf(baiDangDto.getLoai()));
            if (baiDangDto.getTrangThai().equals("CON_HAN")) {
                baiDang.setTrangThai(TrangThaiBaiDang.CON_HAN);
            }
            else if(baiDangDto.getTrangThai().equals("KHOA_BOI_DN")) {
                baiDang.setTrangThai(TrangThaiBaiDang.KHOA_BOI_DN);
            }
            else if(baiDangDto.getTrangThai().equals("KHOA_BOI_ADMIN")) {
                baiDang.setTrangThai(TrangThaiBaiDang.KHOA_BOI_ADMIN);
            }
            baiDang.setCapNhatVaoLuc(LocalDateTime.now());
            baiDangDto.setMaKyNangs(Arrays.asList(baiDangDto.getMaKyNangsString().split(",")));
            baiDang = baiDangRepository.save(baiDang);

            // Xóa hết kỹ năng cũ
            kyNangBaiDangRepository.deleteAllByBaiDang(baiDang);

            if (baiDangDto.getMaKyNangs() != null && !baiDangDto.getMaKyNangs().isEmpty()) {
                for (String maKyNang : baiDangDto.getMaKyNangs()) {
                    KyNang kyNang = kyNangRepository.findById(maKyNang)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy kỹ năng với mã: " + maKyNang));

                    KyNangBaiDang kyNangBaiDang = new KyNangBaiDang();
                    kyNangBaiDang.setBaiDang(baiDang);
                    kyNangBaiDang.setKyNang(kyNang);
                    kyNangBaiDang.setTaoVaoLuc(LocalDateTime.now());
                    kyNangBaiDang.setCapNhatVaoLuc(LocalDateTime.now());

                    kyNangBaiDangRepository.save(kyNangBaiDang);
                }
            }
            return baiDang;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
