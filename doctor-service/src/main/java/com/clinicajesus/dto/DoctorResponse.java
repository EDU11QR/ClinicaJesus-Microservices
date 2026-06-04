package com.clinicajesus.dto;

public record DoctorResponse(

        Long id,
        String nombres,
        String apellidos,
        String cmp,
        String especialidad,
        Boolean activo

) {
}