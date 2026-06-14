package com.clinicajesus.service;

import com.clinicajesus.dto.CitaRequest;
import com.clinicajesus.dto.CitaResponse;

import java.util.List;

public interface CitaService {

    CitaResponse crear(CitaRequest request);

    List<CitaResponse> listar();

    CitaResponse buscarPorId(Long id);

}