package com.clinicajesus.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDisponibleResponse(
        Long id,
        Long doctorId,
        LocalDate fecha,
        LocalTime horaInicio,
        LocalTime horaFin,
        Boolean activo
) {
}
