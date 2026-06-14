package com.clinicajesus.dto;

public record CitaRequest(
        Long pacienteId,
        Long horarioDisponibleId,
        String motivoConsulta
) {
}
