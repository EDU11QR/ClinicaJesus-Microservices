package com.clinicajesus.notification_service.controller;

import com.clinicajesus.notification_service.dto.EmailAdjuntoRequest;
import com.clinicajesus.notification_service.dto.EmailRequest;
import com.clinicajesus.notification_service.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class EmailController {

    private final EmailService emailService;

    public EmailController(
            EmailService emailService
    ) {
        this.emailService = emailService;
    }

    @PostMapping("/correo")
    public ResponseEntity<String> enviarCorreo(
            @RequestBody EmailRequest request
    ) {

        emailService.enviarCorreo(request);

        return ResponseEntity.ok(
                "Correo enviado correctamente"
        );
    }

    @PostMapping("/correo-adjunto")
    public ResponseEntity<String>
    enviarCorreoConAdjunto(
            @RequestBody EmailAdjuntoRequest request
    ) {

        emailService.enviarCorreoConAdjunto(
                request
        );

        return ResponseEntity.ok(
                "Correo con adjunto enviado"
        );
    }

}