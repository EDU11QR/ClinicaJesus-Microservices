package com.clinicajesus.notification_service.service;

import com.clinicajesus.notification_service.dto.EmailRequest;

public interface EmailService {

    void enviarCorreo(
        EmailRequest request
    );

}
