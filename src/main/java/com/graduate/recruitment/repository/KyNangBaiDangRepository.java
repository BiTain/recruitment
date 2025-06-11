package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.entity.KyNangBaiDang;
import com.graduate.recruitment.entity.KyNangBaiDangId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KyNangBaiDangRepository extends JpaRepository<KyNangBaiDang, KyNangBaiDangId> {
    void deleteAllByBaiDang(BaiDang baiDang);

}
