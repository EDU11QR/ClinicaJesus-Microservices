package com.clinicajesus.dto;

public record LoginResponse(
        Long id,
        String username,
        String nombres,
        String apellidos,
        String email,
        String telefono,
        //rolUsusario rol,
        Boolean activo
) {
}
