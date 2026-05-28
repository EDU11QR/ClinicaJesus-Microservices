package com.clinicajesus.controller;

import com.clinicajesus.dto.AuthResponse;
import com.clinicajesus.dto.LoginRequest;
import com.clinicajesus.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        private final UsuarioService usuarioService;

        public AuthController(UsuarioService usuarioService) {
            this.usuarioService = usuarioService;
        }

        // LOGIN
        @PostMapping("/login")
        public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(usuarioService.login(request));
        }

        @GetMapping("/test")
        public ResponseEntity<String> test() {

            return ResponseEntity.ok(
                    "Endpoint privado funcionando correctamente"
            );
        }

        // ENDPOINTS de roles   
        @GetMapping("/admin")
        public ResponseEntity<String> admin() {

            return ResponseEntity.ok(
                    "Bienvenido ADMIN"
            );
        }

        @GetMapping("/doctor")
        public ResponseEntity<String> doctor() {

            return ResponseEntity.ok(
                    "Bienvenido DOCTOR"
            );
        }

        @GetMapping("/paciente")
        public ResponseEntity<String> paciente() {

            return ResponseEntity.ok(
                    "Bienvenido PACIENTE"
            );
        }



}

