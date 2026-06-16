package com.clinicajesus.dto;

import java.math.BigDecimal;

public record PagoRequest(

        Long citaId,

        Long pacienteId,

        BigDecimal monto,

        String metodoPago

) {
}