package com.clinicajesus.payment_service.client;

import com.clinicajesus.payment_service.dto.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/api/auth/usuarios/{id}")
    UsuarioResponse obtenerUsuario(
            @PathVariable Long id
    );
}