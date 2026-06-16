package com.clinicajesus.payment_service.repository;

import com.clinicajesus.payment_service.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository
        extends JpaRepository<PagoEntity, Long> {

    List<PagoEntity> findByPacienteId(Long pacienteId);

    List<PagoEntity> findByCitaId(Long citaId);

}