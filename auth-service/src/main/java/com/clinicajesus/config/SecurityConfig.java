package com.clinicajesus.config;

import com.clinicajesus.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                // Desactivar CSRF
                .csrf(csrf -> csrf.disable())

                // Configurar endpoints
                .authorizeHttpRequests(auth -> auth

                        // Públicos
                        .requestMatchers("/api/auth/login").permitAll()

                        .requestMatchers("/api/auth/admin")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/auth/doctor")
                        .hasRole("DOCTOR")

                        .requestMatchers("/api/auth/paciente")
                        .hasRole("PACIENTE")

                        .anyRequest().authenticated()
                )

                // Stateless JWT
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Registrar filtro JWT
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}