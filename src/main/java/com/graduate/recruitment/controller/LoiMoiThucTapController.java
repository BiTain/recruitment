package com.graduate.recruitment.controller;

import com.graduate.recruitment.service.LoiMoiThucTapService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class LoiMoiThucTapController {
    private LoiMoiThucTapService loiMoiThucTapService;

    @PutMapping("/sinh-vien/loi-moi-thuc-tap/xac-nhan")
    public String xacNhanLMTT(RedirectAttributes redirectAttributes,
                              @RequestParam("maLMTT") String maLMTT,
                              @RequestParam("trangThai") String trangThai){
        try {
            loiMoiThucTapService.chapNhanOrTuChoi(maLMTT, trangThai);
            if (trangThai.equals("chap-nhan")){
                redirectAttributes.addFlashAttribute("successMessage",
                        "Bạn đã xác nhận tham gia thực tập thành công!");
            }else if (trangThai.equals("tu-choi")){
                redirectAttributes.addFlashAttribute("successMessage",
                        "Bạn đã từ chối tham gia thực tập!");
            }
            return "redirect:/sinh-vien/lich-phong-van";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/sinh-vien/lich-phong-van";
        }
    }
}
