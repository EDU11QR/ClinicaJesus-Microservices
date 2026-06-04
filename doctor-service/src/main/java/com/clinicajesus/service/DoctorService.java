package com.clinicajesus.service;

import com.clinicajesus.dto.DoctorRequest;
import com.clinicajesus.dto.DoctorResponse;

import java.util.List;

public interface DoctorService {

    DoctorResponse crear(DoctorRequest request);

    List<DoctorResponse> listar();
}