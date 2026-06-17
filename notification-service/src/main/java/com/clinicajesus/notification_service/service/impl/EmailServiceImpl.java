package com.clinicajesus.notification_service.service.impl;

import com.clinicajesus.notification_service.dto.EmailRequest;
import com.clinicajesus.notification_service.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl
        implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remitente;

    public EmailServiceImpl(
            JavaMailSender mailSender
    ) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarCorreo(
            EmailRequest request
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setFrom(
                remitente
        );

        message.setTo(
                request.destinatario()
        );

        message.setSubject(
                request.asunto()
        );

        message.setText(
                request.mensaje()
        );

        mailSender.send(
                message
        );
    }
}