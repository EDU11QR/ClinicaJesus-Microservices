package com.clinicajesus.service.impl;

import com.clinicajesus.dto.AuthResponse;
import com.clinicajesus.dto.LoginRequest;
import com.clinicajesus.entity.UsuarioEntity;
import com.clinicajesus.repository.UsuarioRepository;
import com.clinicajesus.security.JwtService;
import com.clinicajesus.service.UsuarioService;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest request){

        UsuarioEntity usuario = usuarioRepository
                .findByUsernameOrEmail(
                        request.usernameOrEmail(),
                        request.usernameOrEmail()
                )
                .orElseThrow(() -> new RuntimeException("Credenciales invalidas"));

        if (!request.password().equals(usuario.getPassword())) {
            throw new RuntimeException("Credenciales invalidas");
        }

        String token = jwtService.generateToken(usuario.getUsername());

        return new AuthResponse(
                token,
                "Bearer",
                usuario.getUsername()
        );
    }
}