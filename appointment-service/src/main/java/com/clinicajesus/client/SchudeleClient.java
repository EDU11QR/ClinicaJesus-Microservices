package com.clinicajesus.client;

import com.clinicajesus.dto.HorarioDisponibleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "schedule-service")
public interface ScheduleClient {

    @GetMapping("/api/horarios/{id}")
    HorarioDisponibleResponse obtenerHorario(
            @PathVariable Long id
    );
}