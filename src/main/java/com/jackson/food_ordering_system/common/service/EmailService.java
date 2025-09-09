package com.jackson.food_ordering_system.common.service;

import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final freemarker.template.Configuration freemarkerConfig;

    public void sendRestaurantApprovalEmail(String to, String subject, Map<String, Object> model){
        try {
            Template template = freemarkerConfig.getTemplate("email/restaurant-approval.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            javaMailSender.send(message);

        } catch (Exception e){
            throw new RuntimeException("Failed to send email", e);
        }

    }


    public void sendStaffInviteEmail(String to, String subject, String text){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

        } catch (MessagingException e){
            throw new RuntimeException("Failed to send email", e);
        }
    }


}
