package com.clinicajesus.service.impl;

import com.clinicajesus.dto.LoginRequest;
import com.clinicajesus.dto.LoginResponse;
import com.clinicajesus.entity.UsuarioEntity;
import com.clinicajesus.repository.UsuarioRepository;
import com.clinicajesus.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request){
        UsuarioEntity usuario = usuarioRepository
                .findByUsernameOrEmail(
                        request.usernameOrEmail(),
                        request.usernameOrEmail()
                )
                .orElseThrow(() -> new RuntimeException("Credenciales invalidas"));

        boolean passwordOk = passwordEncoder.matches(
                request.password(),
                usuario.getPassword()
        );

        if (!passwordOk){
            throw new RuntimeException("Credenciales invalidas");
        }

        return new LoginResponse(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getActivo()
        );

    }

}
