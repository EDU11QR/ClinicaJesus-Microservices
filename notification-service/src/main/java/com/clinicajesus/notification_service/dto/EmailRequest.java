package com.clinicajesus.notification_service.dto;

public record EmailRequest(

        String destinatario,

        String asunto,

        String mensaje

) {
}