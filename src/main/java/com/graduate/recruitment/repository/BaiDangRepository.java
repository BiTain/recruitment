package com.graduate.recruitment.repository;

import com.graduate.recruitment.entity.BaiDang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface BaiDangRepository extends JpaRepository<BaiDang,String>, JpaSpecificationExecutor<BaiDang> {
}
