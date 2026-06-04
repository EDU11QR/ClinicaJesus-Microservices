package com.clinicajesus.dto;

public record DoctorRequest(

        Long usuarioId,
        String nombres,
        String apellidos,
        String cmp,
        String especialidad

) {
}