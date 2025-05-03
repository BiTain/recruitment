package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.service.business.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ResumeController {
    private ResumeService resumeService;

    @GetMapping("/doanh-nghiep/ho-so")
    public String getAllResume(Model model,
                               @RequestParam(value = "maDoanhNghiep") String maDoanhNghiep,
                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                               @RequestParam(value = "status", defaultValue = "dang-cho", required = false)
                               String status
    ) {
//        if(status.equals("dang-cho")){
//            model.addAttribute("status", "dang-cho");
//        } else if (status.equals("da-thong-qua")) {
//            model.addAttribute("status", "da-thong-qua");
//        }
//        else if (status.equals("da-tu-choi")) {
//            model.addAttribute("status", "da-tu-choi");
//        } else if (status.equals("dang-cho-phong-van")) {
//            model.addAttribute("status", "dang-cho-phong-van");
//        }
        model.addAttribute("danhSachHoSo", resumeService.getAllResumeByStatus(maDoanhNghiep,status, page, limit).getContent());
        model.addAttribute("status",status);
        return "business/resume/list";
    }

}
