package com.clinicajesus.dto;

public record AuthResponse(

        String token,
        String type,
        String username

) {
}