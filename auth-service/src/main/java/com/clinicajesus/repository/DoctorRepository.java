package com.clinicajesus.repository;

import com.clinicajesus.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository
        extends JpaRepository<DoctorEntity, Long> {
}