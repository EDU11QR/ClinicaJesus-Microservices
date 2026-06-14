package com.clinicajesus.controller;

import com.clinicajesus.dto.*;
import com.clinicajesus.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        @GetMapping("/encrypt/{password}")
        public String encrypt(@PathVariable String password) {

            return new BCryptPasswordEncoder().encode(password);
        }

        @PostMapping("/register")
        public ResponseEntity<AuthResponse> register(
                @RequestBody RegisterRequest request
        ){
            return ResponseEntity.ok(
                    usuarioService.register(request)
            );
        }

        @GetMapping("/usuarios/{id}")
        public ResponseEntity<UsuarioResponse> obtenerPorId(
                @PathVariable Long id
        ){
            return ResponseEntity.ok(
                    usuarioService.obtenerPorId(id)
            );
        }

        @GetMapping("/profile")
        public ResponseEntity<ProfileResponse> profile(
                Authentication authentication
        ){
            String username = authentication.getName();

            String rol = authentication
                    .getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();

            return ResponseEntity.ok(
                    new ProfileResponse(
                            username,
                            rol
                    )
            );

        }


}

