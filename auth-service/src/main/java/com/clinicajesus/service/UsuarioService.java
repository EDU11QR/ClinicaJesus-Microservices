package com.clinicajesus.service;

import com.clinicajesus.dto.AuthResponse;
import com.clinicajesus.dto.LoginRequest;
import com.clinicajesus.dto.LoginResponse;

public interface UsuarioService {

    AuthResponse login(LoginRequest request);

}
