package com.graduate.recruitment.controller;

import com.graduate.recruitment.dto.BaiDangDto;
import com.graduate.recruitment.entity.BaiDang;
import com.graduate.recruitment.service.BaiDangService;
import com.graduate.recruitment.service.SinhVienBaiDangService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class BaiDangController {
    private BaiDangService baiDangService;

    @GetMapping("/sinh-vien/bai-dang")
    public String showAllBaiDang(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 @RequestParam(value = "search", required = false) String tuKhoa,
                                 @RequestParam(value = "kyNang", required = false) String kyNang,
                                 @RequestParam(value = "selectedJob", required = false) String maBaiDang,
                                 Model model){
        List<BaiDangDto> baiDangs = baiDangService.getAll(page, limit, kyNang,tuKhoa).getContent();
        model.addAttribute("baiDangs",baiDangs);
        if (maBaiDang == null && !baiDangs.isEmpty()){
            maBaiDang = baiDangs.get(0).getMaBaiDang();
        }
        if (maBaiDang!=null) {
            BaiDangDto baiDangSelected = baiDangService.getByMaBaiDang(maBaiDang);
            model.addAttribute("selectedJob",baiDangSelected);
            model.addAttribute("maBaiDang", maBaiDang);
        }
        model.addAttribute("maKyNang", kyNang);
        return "student/job/list";
    }

    @GetMapping("/sinh-vien/bai-dang/{id}")
    public String showBaiDang(@PathVariable String id,
                              Model model){
        model.addAttribute("baiDang",baiDangService.getByMaBaiDang(id));
        return "student/job/detail";
    }

}
