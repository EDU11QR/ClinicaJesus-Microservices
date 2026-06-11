package com.clinicajesus.client;

import com.clinicajesus.dto.DoctorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service")
public interface DoctorClient {

    @GetMapping("/api/doctores/{id}")
    DoctorResponse obtenerDoctor(
            @PathVariable Long id
    );
}