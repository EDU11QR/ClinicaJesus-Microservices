package com.clinicajesus.dto;

import com.clinicajesus.enums.EstadoCita;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CitaResponse(

        Long id,
        Long pacienteId,
        Long doctorId,
        Long horarioDisponibleId,

        BigDecimal precio,
        String moneda,

        EstadoCita estado,

        String motivoConsulta,
        String observaciones,

        LocalDateTime fechaHoraCreacion

) {
}