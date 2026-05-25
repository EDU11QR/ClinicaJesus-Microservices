package com.clinicajesus.controller;

import com.clinicajesus.dto.LoginRequest;
import com.clinicajesus.dto.LoginResponse;
import com.clinicajesus.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

        private final UsuarioService usuarioService;

        // LOGIN
        @PostMapping("/login")
        public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(usuarioService.login(request));
        }
    }

