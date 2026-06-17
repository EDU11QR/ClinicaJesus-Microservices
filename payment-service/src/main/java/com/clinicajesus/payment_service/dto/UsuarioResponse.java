package com.clinicajesus.payment_service.dto;

public record UsuarioResponse(
        Long id,
        String username,
        String nombres,
        String apellidos,
        String email,
        String telefono,
        String rol,
        Boolean activo
) {
}