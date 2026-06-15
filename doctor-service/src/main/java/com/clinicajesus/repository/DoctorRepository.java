package com.clinicajesus.repository;

import com.clinicajesus.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository
        extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByCmp(String cmp);
    List<DoctorEntity>findByActivoTrue();
}