package com.clinicajesus.service;

import com.clinicajesus.dto.CitaRequest;
import com.clinicajesus.dto.CitaResponse;

import java.time.LocalDate;
import java.util.List;

public interface CitaService {

    CitaResponse crear(CitaRequest request);

    List<CitaResponse> listar();

    CitaResponse buscarPorId(Long id);

    List<CitaResponse> listarPorPaciente(Long pacienteId);

    void cancelar(Long id);

    CitaResponse cambiarEstado(
            Long id,
            String estado
    );

    List<CitaResponse> listarPorDoctor(
            Long doctorId
    );

    List<CitaResponse> listarPorDoctorYFecha(
            Long doctorId,
            LocalDate fecha
    );
}