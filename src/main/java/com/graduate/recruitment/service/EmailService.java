package com.graduate.recruitment.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String body, String hoVaTen) {

        // 1. Chuẩn bị nội dung HTML từ Thymeleaf
        Context context = new Context();
        context.setVariable("hoTen", hoVaTen);
        context.setVariable("noiDung", body); // dùng `body` truyền vào
//        context.setVariable("linkAction", "http://your-app.com/chi-tiet-loi-moi/123");
//        context.setVariable("tenNut", "Xem lời mời");

        String htmlContent = templateEngine.process("email-notification", context);

        // 2. Tạo MimeMessage để gửi nội dung HTML
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom("d0763705638@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // TRUE để dùng HTML

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Không gửi được email", e);
        }
    }
}
