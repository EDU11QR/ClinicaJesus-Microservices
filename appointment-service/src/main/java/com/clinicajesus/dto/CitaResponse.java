package com.clinicajesus.dto;

import com.clinicajesus.enums.EstadoCita;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CitaResponse(

        Long id,
        Long pacienteId,
        Long doctorId,
        Long horarioDisponibleId,

        LocalDate fecha,
        LocalTime horaInicio,
        LocalTime horaFin,

        String motivoConsulta,
        EstadoCita estado,
        LocalDateTime fechaHoraCreacion

) {
}