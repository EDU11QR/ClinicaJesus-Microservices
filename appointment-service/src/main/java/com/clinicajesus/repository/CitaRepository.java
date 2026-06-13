package com.clinicajesus.repository;

import com.clinicajesus.entity.CitaEntity;
import com.clinicajesus.enums.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository
        extends JpaRepository<CitaEntity, Long> {

    boolean existsByHorarioDisponibleIdAndEstadoIn(
            Long horarioDisponibleId,
            List<EstadoCita> estados
    );

}