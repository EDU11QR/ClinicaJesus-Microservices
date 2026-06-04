package com.clinicajesus.service.impl;

import com.clinicajesus.dto.DoctorRequest;
import com.clinicajesus.dto.DoctorResponse;
import com.clinicajesus.entity.DoctorEntity;
import com.clinicajesus.repository.DoctorRepository;
import com.clinicajesus.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(
            DoctorRepository doctorRepository
    ) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorResponse crear(
            DoctorRequest request
    ) {

        DoctorEntity doctor = DoctorEntity.builder()
                .nombres(request.nombres())
                .apellidos(request.apellidos())
                .cmp(request.cmp())
                .especialidad(request.especialidad())
                .activo(true)
                .build();

        doctor = doctorRepository.save(doctor);

        return new DoctorResponse(
                doctor.getId(),
                doctor.getNombres(),
                doctor.getApellidos(),
                doctor.getCmp(),
                doctor.getEspecialidad(),
                doctor.getActivo()
        );
    }

    @Override
    public List<DoctorResponse> listar() {

        return doctorRepository.findAll()
                .stream()
                .map(doctor ->
                        new DoctorResponse(
                                doctor.getId(),
                                doctor.getNombres(),
                                doctor.getApellidos(),
                                doctor.getCmp(),
                                doctor.getEspecialidad(),
                                doctor.getActivo()
                        )
                )
                .toList();
    }
}