package com.clinicajesus.dto;

import com.clinicajesus.enums.EstadoCita;

import java.time.LocalDateTime;

public record CitaResponse(

        Long id,
        Long pacienteId,
        Long horarioDisponibleId,
        String motivoConsulta,
        EstadoCita estado,
        LocalDateTime fechaHoraCreacion

) {
}