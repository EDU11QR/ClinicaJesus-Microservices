package com.clinicajesus.dto;

public record DoctorResponse(

        Long id,
        Long usuarioId,
        String nombres,
        String apellidos,
        String cmp,
        String especialidad,
        Boolean activo

) {
}