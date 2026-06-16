package com.clinicajesus.payment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "appointment-service")
public interface AppointmentClient {

    @PutMapping("/api/citas/{id}/estado")
    void confirmarCita(
            @PathVariable Long id,
            @RequestParam String estado
    );

}