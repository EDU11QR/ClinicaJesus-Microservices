package com.clinicajesus.dto;

public record DoctorRequest(

        String nombres,
        String apellidos,
        String cmp,
        String especialidad

) {
}