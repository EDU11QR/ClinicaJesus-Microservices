package com.clinicajesus.payment_service.service;

import com.clinicajesus.payment_service.dto.PagoRequest;
import com.clinicajesus.payment_service.dto.PagoResponse;

import java.util.List;

public interface PagoService {

    PagoResponse crear(PagoRequest request);

    List<PagoResponse> listar();

    PagoResponse buscarPorId(Long id);

    List<PagoResponse> listarPorPaciente(Long pacienteId);

    PagoResponse cambiarEstado(
            Long id,
            String estado
    );

    byte[] descargarComprobante(
            Long pagoId
    );

}