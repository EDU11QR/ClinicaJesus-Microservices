package com.clinicajesus.service.impl;

import com.clinicajesus.dto.AuthResponse;
import com.clinicajesus.dto.LoginRequest;
import com.clinicajesus.entity.UsuarioEntity;
import com.clinicajesus.repository.UsuarioRepository;
import com.clinicajesus.security.JwtService;
import com.clinicajesus.service.UsuarioService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(LoginRequest request){

        UsuarioEntity usuario = usuarioRepository
                .findByUsernameOrEmail(
                        request.usernameOrEmail(),
                        request.usernameOrEmail()
                )
                .orElseThrow(() -> new RuntimeException("Credenciales invalidas"));

        if (!passwordEncoder.matches(
                request.password(),
                usuario.getPassword()
        )) {
            throw new RuntimeException("Credenciales invalidas");
        }

        String token = jwtService.generateToken(
                usuario.getUsername(),
                usuario.getRol().name()
                );

        return new AuthResponse(
                token,
                "Bearer",
                usuario.getUsername()
        );
    }
}