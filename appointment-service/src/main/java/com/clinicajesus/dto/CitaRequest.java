package com.clinicajesus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CitaRequest(

        @NotNull(message = "pacienteId es Obligatorio")
        Long pacienteId,

        @NotNull(message = "HorarioDisponibleId es Obligatorio")
        Long horarioDisponibleId,

        @NotBlank(message = "motivoConsulta es obligatorio")
        @Size(max = 200, message = "El motivo de la consulta no puede superar los 500 carateres")
        String motivoConsulta
) {
}
