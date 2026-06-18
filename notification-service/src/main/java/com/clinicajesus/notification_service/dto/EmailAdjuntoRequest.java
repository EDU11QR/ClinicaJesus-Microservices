package com.clinicajesus.notification_service.dto;

public record EmailAdjuntoRequest(

        String destinatario,

        String asunto,

        String mensaje,

        byte[] archivo,

        String nombreArchivo

) {
}