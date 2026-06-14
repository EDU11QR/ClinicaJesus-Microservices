package com.clinicajesus.dto;

import com.clinicajesus.enums.RolUsuario;

public record UsuarioResponse(
        Long id,
        String username,
        String nombres,
        String apellidos,
        String email,
        String telefono,
        RolUsuario rol,
        Boolean activo
) {
}
