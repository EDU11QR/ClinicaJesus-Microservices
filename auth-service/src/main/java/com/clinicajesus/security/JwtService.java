package com.clinicajesus.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Clave secreta
    private static final String SECRET_KEY =
            "clinicajesusmicroservicesjwtsecretkey2026";

    // 24 horas
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // Generar key
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generar token
        public String generateToken(String username, String rol) {

            return Jwts.builder()
                    .subject(username)
                    .claim("rol", rol)
                    .issuedAt(new Date())
                    .expiration(
                            new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                    )
                    .signWith(getSigningKey())
                    .compact();
        }



    // Extraer username
    public String extractUsername(String token) {

        return extractClaims(token).getSubject();
    }

    // Extraer rol
    public String extractRol(String token) {

        Claims claims = extractClaims(token);

        return claims.get("rol", String.class);
    }

    // Validar token
    public boolean isTokenValid(String token, String username) {

        final String extractedUsername = extractUsername(token);

        return extractedUsername.equals(username)
                && !isTokenExpired(token);
    }

    // Validar expiración
    private boolean isTokenExpired(String token) {

        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Extraer claims
    private Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}