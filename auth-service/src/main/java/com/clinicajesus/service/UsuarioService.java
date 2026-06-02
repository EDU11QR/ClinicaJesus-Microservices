package com.clinicajesus.service;

import com.clinicajesus.dto.AuthResponse;
import com.clinicajesus.dto.LoginRequest;
import com.clinicajesus.dto.RegisterRequest;

public interface UsuarioService {

    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);

}
