package com.clinicajesus.payment_service.dto;

public record EmailRequest(

        String destinatario,

        String asunto,

        String mensaje

) {
}