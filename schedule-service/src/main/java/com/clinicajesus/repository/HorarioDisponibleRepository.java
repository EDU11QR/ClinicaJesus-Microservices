package com.clinicajesus.repository;

import com.clinicajesus.entity.HorarioDisponibleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface HorarioDisponibleRepository extends JpaRepository<HorarioDisponibleEntity, Long> {

    boolean existsByDoctorIdAndFechaAndHoraInicio(
            Long doctorId,
            LocalDate fecha,
            LocalTime horaInicio
    );
}
