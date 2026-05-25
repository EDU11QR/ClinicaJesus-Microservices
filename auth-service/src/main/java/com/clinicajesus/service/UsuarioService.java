package com.clinicajesus.service;

import com.clinicajesus.dto.LoginRequest;
import com.clinicajesus.dto.LoginResponse;

public interface UsuarioService {

    LoginResponse login(LoginRequest request);

}
