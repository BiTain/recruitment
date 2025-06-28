package com.graduate.recruitment.service;

import com.graduate.recruitment.entity.TaiKhoan;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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

    public void sendEmail(String to, String subject, String body, String hoVaTen, String from) {

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

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // TRUE để dùng HTML

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Không gửi được email", e);
        }
    }

    public void sendEmailByAdmin(String to, String subject, String body, String hoVaTen, String from) {

        // 1. Chuẩn bị nội dung HTML từ Thymeleaf
        Context context = new Context();
        context.setVariable("hoTen", hoVaTen);
        context.setVariable("noiDung", body); // dùng `body` truyền vào

        String htmlContent = templateEngine.process("email-notification-admin", context);

        // 2. Tạo MimeMessage để gửi nội dung HTML
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // TRUE để dùng HTML

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Không gửi được email", e);
        }
    }

    public void sendVerificationEmail(TaiKhoan taiKhoan) {
        String subject = "Xác nhận đăng ký";
        String confirmationUrl = "http://localhost:8080/kich-hoat?maTaiKhoan=" + taiKhoan.getMaTaiKhoan();

        String content = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<h2>Chào mừng bạn đến với hệ thống!</h2>" +
                "<p>Vui lòng xác nhận đăng ký bằng cách nhấn vào nút bên dưới:</p>" +
                "<a href='" + confirmationUrl + "' " +
                "style='display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #28a745; " +
                "text-decoration: none; border-radius: 5px;'>Xác nhận email</a>" +
                "<p>Nếu bạn không thực hiện yêu cầu này, hãy bỏ qua email này.</p>" +
                "</body></html>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("admin@admin.com");
            helper.setTo(taiKhoan.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true); // true = gửi HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // hoặc log
        }
    }

    public void sendResetPasswordEmail(TaiKhoan taiKhoan) {
        String subject = "Đặt lại mật khẩu";
        String resetUrl = "http://localhost:8080/sinh-vien/dat-lai-mat-khau?maTaiKhoan=" + taiKhoan.getMaTaiKhoan();

        String content = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<h2>Yêu cầu đặt lại mật khẩu</h2>" +
                "<p>Để đặt lại mật khẩu, hãy nhấn vào nút bên dưới:</p>" +
                "<a href='" + resetUrl + "' " +
                "style='display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #007bff; " +
                "text-decoration: none; border-radius: 5px;'>Đặt lại mật khẩu</a>" +
                "<p>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>" +
                "</body></html>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("admin@admin.com");
            helper.setTo(taiKhoan.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // log lỗi
        }
    }

    public void sendResetPasswordEmailWithFrom(TaiKhoan taiKhoan, String from) {
        String subject = "Đặt lại mật khẩu";
        String resetUrl = "http://localhost:8080/nha-truong/dat-lai-mat-khau?maTaiKhoan=" + taiKhoan.getMaTaiKhoan();

        String content = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<h2>Yêu cầu đặt lại mật khẩu</h2>" +
                "<p>Để đặt lại mật khẩu, hãy nhấn vào nút bên dưới:</p>" +
                "<a href='" + resetUrl + "' " +
                "style='display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #007bff; " +
                "text-decoration: none; border-radius: 5px;'>Đặt lại mật khẩu</a>" +
                "<p>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>" +
                "</body></html>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(taiKhoan.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // log lỗi
        }
    }

    public void sendResetPasswordEmailWithFrom(TaiKhoan taiKhoan, String from, String url) {
        String subject = "Đặt lại mật khẩu";
        String resetUrl = String.format("http://localhost:8080/%s/dat-lai-mat-khau?maTaiKhoan=%s", url, taiKhoan.getMaTaiKhoan());

        String content = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<h2>Yêu cầu đặt lại mật khẩu</h2>" +
                "<p>Để đặt lại mật khẩu, hãy nhấn vào nút bên dưới:</p>" +
                "<a href='" + resetUrl + "' " +
                "style='display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #007bff; " +
                "text-decoration: none; border-radius: 5px;'>Đặt lại mật khẩu</a>" +
                "<p>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>" +
                "</body></html>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(taiKhoan.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // log lỗi
        }
    }


}
