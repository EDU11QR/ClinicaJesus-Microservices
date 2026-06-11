package com.clinicajesus.service;

import com.clinicajesus.dto.HorarioDisponibleRequest;
import com.clinicajesus.dto.HorarioDisponibleResponse;

import java.util.List;

public interface HorarioDisponibleService {

    HorarioDisponibleResponse crear(HorarioDisponibleRequest request);

    List<HorarioDisponibleResponse> listar();

    HorarioDisponibleResponse buscarPorId(Long id);

    HorarioDisponibleResponse actualizar(
            Long id,
            HorarioDisponibleRequest request
    );

    void eliminar(Long id);
}