package com.clinicajesus.service;

import com.clinicajesus.dto.DoctorRequest;
import com.clinicajesus.dto.DoctorResponse;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {

    DoctorResponse crear(DoctorRequest request);

    List<DoctorResponse> listar();

    DoctorResponse buscarPorId(Long id);

    DoctorResponse actualizar(Long id, DoctorRequest request);

    void eliminar(Long id);
}