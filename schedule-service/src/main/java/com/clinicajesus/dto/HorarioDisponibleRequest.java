package com.clinicajesus.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDisponibleRequest(
        Long doctorId,
        LocalDate fecha,
        LocalTime horaInicio
) {
}
