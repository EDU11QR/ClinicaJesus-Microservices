package com.clinicajesus.dto;

public record RegisterRequest(

        String username,
        String password,
        String nombres,
        String apellidos,
        String email,
        String telefono,
        String rol
) {

}
