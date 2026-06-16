package com.clinicajesus.payment_service.dto;

import com.clinicajesus.payment_service.enums.MetodoPago;

import java.math.BigDecimal;

public record PagoRequest(

        Long citaId,

        Long pacienteId,

        BigDecimal monto,

        MetodoPago metodoPago

) {
}