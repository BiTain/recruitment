package com.graduate.recruitment.controller.business;

import com.graduate.recruitment.config.CustomUserPrincipal;
import com.graduate.recruitment.dto.DoanhNghiepDto;
import com.graduate.recruitment.entity.DoanhNghiep;
import com.graduate.recruitment.entity.TaiKhoan;
import com.graduate.recruitment.repository.DoanhNghiepRepository;
import com.graduate.recruitment.repository.TaiKhoanRepository;
import com.graduate.recruitment.service.DoanhnghiepService;
import com.graduate.recruitment.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class BusinessController {
    private DoanhnghiepService doanhnghiepService;
    private DoanhNghiepRepository doanhNghiepRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private FileService fileService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/doanh-nghiep")
    public String home(Model model) {
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DoanhNghiep currentDN = customUserPrincipal.getDoanhNghiep();
        model.addAttribute("doanhNghiep", doanhnghiepService.getDoanhNghiepById(currentDN.getMaDoanhNghiep()));
        return "business/home";
    }

    @PostMapping("/doanh-nghiep/cap-nhap-co-ban/{maDoanhNghiep}")
    public String capNhapThongTinDoanhNghiepCoBan(@PathVariable("maDoanhNghiep") String id,
                                                  @ModelAttribute("business") DoanhNghiepDto doanhNghiep,
                                                  RedirectAttributes redirectAttributes) {
        try {
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


            doanhNghiepRepository.save(doanhNghiepInDb);
            redirectAttributes.addFlashAttribute("successMsg","Cập nhật thông tin cơ bản thành công");
            return "redirect:/doanh-nghiep";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg","Cập nhật thông tin cơ bản thất bại");
            return "redirect:/doanh-nghiep";
        }
    }

    @PostMapping("/doanh-nghiep/cap-nhap-chi-tiet/{maDoanhNghiep}")
    public String capNhapThongTinDoanhNghiepChiTiet(@PathVariable("maDoanhNghiep") String id,
                                                    @ModelAttribute("business") DoanhNghiepDto doanhNghiepDto,
                                                    RedirectAttributes redirectAttributes) {
        try {
            DoanhNghiep doanhNghiepInDb = doanhNghiepRepository.findById(id).get();

            doanhNghiepInDb.setMoTa(doanhNghiepDto.getMoTa());
            doanhNghiepInDb.setDiaChi(String.format("%s, %s, %s", doanhNghiepDto.getChiTietDiaChi(), doanhNghiepDto.getXa(), doanhNghiepDto.getHuyen()));

            doanhNghiepRepository.save(doanhNghiepInDb);
            redirectAttributes.addFlashAttribute("successMsg","Cập nhật thông tin thành công");
            return "redirect:/doanh-nghiep";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg","Cập nhật thông tin thất bại");
            return "redirect:/doanh-nghiep";
        }
    }

    @PostMapping("/doanh-nghiep/cap-nhap-phuc-loi/{maDoanhNghiep}")
    public String capNhapDoanhNghiepPhucLoi(@PathVariable("maDoanhNghiep") String id,
                                            @RequestParam("phuc-loi") String phucLoiMoi,
                                            RedirectAttributes redirectAttributes) {
        try {
            DoanhNghiep doanhNghiepInDb = doanhNghiepRepository.findById(id).get();
            doanhNghiepInDb.setPhucLoi(phucLoiMoi);
            doanhNghiepRepository.save(doanhNghiepInDb);
            redirectAttributes.addFlashAttribute("successMsg","Cập nhật phúc lợi thành công");
            return "redirect:/doanh-nghiep";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMsg","Cập nhật phúc lợi thất bại");
            return "redirect:/doanh-nghiep";
        }
    }

    @GetMapping("/doanh-nghiep/doi-mat-khau")
    public String getPageDoiMatKhau(Model model){
        return "/business/change-password";
    }

    @PostMapping("/doanh-nghiep/doi-mat-khau")
    public String doanhNghiepDoiMatKhau(
            RedirectAttributes redirectAttributes,
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword
    ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) auth.getPrincipal();
        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(customUserPrincipal.getDoanhNghiep().getTaiKhoan().getEmail());
        if(!confirmPassword.equals(newPassword)){
            redirectAttributes.addFlashAttribute("errorMsg", "Mật khẩu nhập lại không chính xác");
        }
        if(passwordEncoder.matches(currentPassword, taiKhoan.getMatKhau())){
            taiKhoan.setMatKhau(passwordEncoder.encode(newPassword));
            taiKhoanRepository.save(taiKhoan);
            redirectAttributes.addFlashAttribute("successMsg", "Đổi mật khẩu thành công");
            return "redirect:/doanh-nghiep/doi-mat-khau";
        }
        else {
            redirectAttributes.addFlashAttribute("errorMsg", "Mật khẩu không đúng");
        }
        return "redirect:/doanh-nghiep/doi-mat-khau";
    }
}
