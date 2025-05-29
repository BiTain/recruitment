package com.graduate.recruitment.controller;

import com.graduate.recruitment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam("to") String to,
                            @RequestParam("subject") String subject,
                            @RequestParam("body") String body,
                            @RequestParam("hoVaTen") String hoVaTen,
                            RedirectAttributes redirectAttributes) {
        try {
            emailService.sendEmail(to, subject, body, hoVaTen);
            redirectAttributes.addFlashAttribute("successMsg", "Gửi email thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", "Gửi email thất bại: " + e.getMessage());
        }

        return "redirect:/doanh-nghiep/sinh-vien";
    }
}
