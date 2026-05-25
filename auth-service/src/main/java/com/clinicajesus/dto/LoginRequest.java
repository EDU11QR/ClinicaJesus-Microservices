package com.clinicajesus.dto;

public record LoginRequest(
        String usernameOrEmail,
        String password
) {
}
