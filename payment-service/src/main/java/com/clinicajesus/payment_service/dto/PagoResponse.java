package com.clinicajesus.payment_service.dto;

import com.clinicajesus.payment_service.enums.EstadoPago;
import com.clinicajesus.payment_service.enums.MetodoPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagoResponse(

        Long id,

        Long citaId,

        Long pacienteId,

        BigDecimal monto,

        MetodoPago metodoPago,

        EstadoPago estado,

        LocalDateTime fechaPago

) {
}