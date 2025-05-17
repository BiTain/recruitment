package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import com.graduate.recruitment.service.DoanhnghiepService;
import com.graduate.recruitment.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@AllArgsConstructor
public class BusinessController {
    private DoanhnghiepService doanhnghiepService;
    private DoanhNghiepRepository doanhNghiepRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private FileService fileService;

    @GetMapping("/doanh-nghiep")
    public String home(Model model) {
        model.addAttribute("doanhNghiep", doanhnghiepService.getDoanhNghiepById("DN001"));
        return "business/home";
    }

    @PostMapping("/doanh-nghiep/cap-nhap-co-ban/{maDoanhNghiep}")
    public String capNhapThongTinDoanhNghiepCoBan(@PathVariable("maDoanhNghiep") String id, @ModelAttribute("business") DoanhNghiepDto doanhNghiep) {
        DoanhNghiep doanhNghiepInDb = doanhNghiepRepository.findById(id).get();

        if (doanhNghiep.getLogoFile() != null && !doanhNghiep.getLogoFile().getOriginalFilename().isBlank()) {
            doanhNghiepInDb.setAnhDaiDien(fileService.store(doanhNghiep.getLogoFile()));
        }

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

    @PostMapping("/doanh-nghiep/cap-nhap-chi-tiet/{maDoanhNghiep}")
    public String capNhapThongTinDoanhNghiepChiTiet(@PathVariable("maDoanhNghiep") String id, @ModelAttribute("business") DoanhNghiepDto doanhNghiepDto) {
        DoanhNghiep doanhNghiepInDb = doanhNghiepRepository.findById(id).get();

        doanhNghiepInDb.setMoTa(doanhNghiepDto.getMoTa());
        doanhNghiepInDb.setDiaChi(String.format("%s, %s, %s", doanhNghiepDto.getChiTietDiaChi(), doanhNghiepDto.getXa(), doanhNghiepDto.getHuyen()));

        doanhNghiepRepository.save(doanhNghiepInDb);
        return "redirect:/doanh-nghiep";
    }

    @PostMapping("/doanh-nghiep/cap-nhap-phuc-loi/{maDoanhNghiep}")
    public String capNhapDoanhNghiepPhucLoi(@PathVariable("maDoanhNghiep") String id, @RequestParam("phuc-loi") String phucLoiMoi) {
        DoanhNghiep doanhNghiepInDb = doanhNghiepRepository.findById(id).get();
        doanhNghiepInDb.setPhucLoi(phucLoiMoi);
        doanhNghiepRepository.save(doanhNghiepInDb);
        return "redirect:/doanh-nghiep";
    }
}
