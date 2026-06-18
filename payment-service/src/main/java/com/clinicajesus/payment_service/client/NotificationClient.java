package com.clinicajesus.payment_service.client;

import com.clinicajesus.payment_service.dto.EmailAdjuntoRequest;
import com.clinicajesus.payment_service.dto.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/api/notificaciones/correo")
    void enviarCorreo(
            @RequestBody EmailRequest request
    );

    @PostMapping("/api/notificaciones/correo-adjunto")
    void enviarCorreoConAdjunto(
            @RequestBody EmailAdjuntoRequest request
    );

}