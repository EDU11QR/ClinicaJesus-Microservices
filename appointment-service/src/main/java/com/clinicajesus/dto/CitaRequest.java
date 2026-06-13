package com.clinicajesus.dto;

public record CitaRequest(
        Long pacienteId,
        Long horarioDisponible,
        String motivoConsulta
) {
}
