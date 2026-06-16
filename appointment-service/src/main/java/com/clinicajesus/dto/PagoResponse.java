package com.clinicajesus.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagoResponse(

        Long id,

        Long citaId,

        Long pacienteId,

        BigDecimal monto,

        String metodoPago,

        String estado,

        LocalDateTime fechaPago

) {
}