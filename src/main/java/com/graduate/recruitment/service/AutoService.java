package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.LichPhongVan;
import com.graduate.recruitment.entity.enums.KetQua;
import com.graduate.recruitment.entity.enums.TrangThaiBaiDang;
import com.graduate.recruitment.entity.enums.TrangThaiPhongVan;
import com.graduate.recruitment.repository.BaiDangRepository;
import com.graduate.recruitment.repository.LichPhongVanRepository;
import com.graduate.recruitment.repository.SinhVienBaiDangRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AutoService {
    private LichPhongVanRepository lichPhongVanRepository;
    private SinhVienBaiDangRepository sinhVienBaiDangRepository;
    private BaiDangRepository baiDangRepository;

    //tự động cập nhật trạng thái lịch phỏng vấn thành hoàn thành
    //nếu qua thời gian phỏng vấn mà doanh nghiệp không bấm hoàn thành
    @Transactional
    @Scheduled(fixedRate = 60000) // 1p chạy 1 lần
    public void tuDongCapNhatTrangThaiLichPhongVan() {
        LocalDateTime now = LocalDateTime.now();
        List<LichPhongVan> danhSachLich = lichPhongVanRepository
                .findByTrangThaiAndNgayPhongVanBefore(TrangThaiPhongVan.DONG_Y, now);

        for (LichPhongVan lich : danhSachLich) {
            lich.setTrangThai(TrangThaiPhongVan.HOAN_THANH);
            lich.setCapNhatVaoLuc(now);
        }

        lichPhongVanRepository.saveAll(danhSachLich);
    }

    //tự động cập nhật trạng thái lịch bài đăng thành hết hạn
    //nếu qua thời gian đến hạn
    @Transactional
    @Scheduled(fixedRate = 60000) // 1p chạy 1 lần
    public void tuDongCapNhatTrangThaiBaiDang() {
        LocalDateTime now = LocalDateTime.now();
        List<BaiDang> danhSachBaiDang = baiDangRepository
                .findByTrangThaiNotInAndDenHanBefore(
                        List.of(TrangThaiBaiDang.HET_HAN, TrangThaiBaiDang.KHOA_BOI_ADMIN), now);

        for (BaiDang baiDang : danhSachBaiDang) {
            baiDang.setTrangThai(TrangThaiBaiDang.HET_HAN);
            baiDang.setCapNhatVaoLuc(now);
        }

        baiDangRepository.saveAll(danhSachBaiDang);
    }

    //tự động cập nhật trạng thái lịch phỏng vấn thành từ chối
    //và kết quả hồ sơ thành từ chối nếu quá hạn xác nhận mà sinh viên không xác nhận
    @Transactional
    @Scheduled(fixedRate = 60000) // 1p chạy 1 lần
    public void tuDongCapNhatTrangThaiPhongVanVaKetQuaHoSo(){
        LocalDateTime now = LocalDateTime.now();
        List<LichPhongVan> danhSachLich = lichPhongVanRepository
                .findByTrangThaiAndHanXacNhanBefore(TrangThaiPhongVan.DANG_CHO, now);

        for (LichPhongVan lich : danhSachLich) {
            lich.setTrangThai(TrangThaiPhongVan.TU_CHOI);
            lich.setCapNhatVaoLuc(now);

            //Cập nhật kết quả ứng tuyển tương ứng
            sinhVienBaiDangRepository.findById(lich.getMaSVBD())
                    .ifPresent(svbd->{
                        svbd.setKetQua(KetQua.TU_CHOI);
                        svbd.setCapNhatVaoLuc(LocalDateTime.now());
                        sinhVienBaiDangRepository.save(svbd);
                    });

            lichPhongVanRepository.save(lich);
        }
    }
}
