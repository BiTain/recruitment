package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import com.graduate.recruitment.service.DoanhnghiepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BusinessController {
    private DoanhnghiepService doanhnghiepService;
    private DoanhNghiepRepository doanhNghiepRepository;
    private TaiKhoanRepository taiKhoanRepository;

    @GetMapping("/doanh-nghiep")
    public String home(Model model) {
        model.addAttribute("doanhNghiep", doanhnghiepService.getDoanhNghiepById("DN001"));
        return "business/home";
    }

    @PostMapping("/doanh-nghiep/cap-nhap-co-ban/{maDoanhNghiep}")
    public String capNhapThongTinDoanhNghiepCoBan(@PathVariable("maDoanhNghiep") String id, @ModelAttribute("business") DoanhNghiep doanhNghiep) {
        DoanhNghiep doanhNghiepInDb = doanhNghiepRepository.findById(id).get();

        doanhNghiepInDb.setTenDoanhNghiep(doanhNghiep.getTenDoanhNghiep());
        doanhNghiepInDb.setMoHinh(doanhNghiep.getMoHinh());
        doanhNghiepInDb.setSoDienThoai(doanhNghiep.getSoDienThoai());
        doanhNghiepInDb.setMaSoThue(doanhNghiep.getMaSoThue());
        doanhNghiepInDb.setTrangDoanhNghiep(doanhNghiep.getTrangDoanhNghiep());
        doanhNghiepInDb.setLinhVuc(doanhNghiep.getLinhVuc());

        TaiKhoan taiKhoan = taiKhoanRepository.findById(doanhNghiep.getTaiKhoan().getMaTaiKhoan()).get();
        taiKhoan.setEmail(doanhNghiep.getTaiKhoan().getEmail());

        doanhNghiepInDb.setTaiKhoan(taiKhoan);

        doanhNghiepRepository.save(doanhNghiepInDb);
        return "redirect:/doanh-nghiep";
    }
}
