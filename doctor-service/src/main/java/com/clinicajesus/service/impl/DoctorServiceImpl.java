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
                .usuarioId(request.usuarioId()  )
                .nombres(request.nombres())
                .apellidos(request.apellidos())
                .cmp(request.cmp())
                .especialidad(request.especialidad())
                .activo(true)
                .build();

        doctor = doctorRepository.save(doctor);

        return new DoctorResponse(
                doctor.getId(),
                doctor.getUsuarioId(),
                doctor.getNombres(),
                doctor.getApellidos(),
                doctor.getCmp(),
                doctor.getEspecialidad(),
                doctor.getActivo()
        );
    }

    @Override
    public List<DoctorResponse> listar() {

        return doctorRepository.findByActivoTrue()
                .stream()
                .map(doctor ->
                        new DoctorResponse(
                                doctor.getId(),
                                doctor.getUsuarioId(),
                                doctor.getNombres(),
                                doctor.getApellidos(),
                                doctor.getCmp(),
                                doctor.getEspecialidad(),
                                doctor.getActivo()
                        )
                )
                .toList();
    }

    @Override
    public DoctorResponse buscarPorId(Long id){

        DoctorEntity doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        if(!doctor.getActivo()){
            throw new RuntimeException(
                    "Doctor no disponible"
            );
        }

        return new DoctorResponse(
                doctor.getId(),
                doctor.getUsuarioId(),
                doctor.getNombres(),
                doctor.getApellidos(),
                doctor.getCmp(),
                doctor.getEspecialidad(),
                doctor.getActivo()
        );
    }

    @Override
    public DoctorResponse actualizar(Long id, DoctorRequest request){

        DoctorEntity doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        doctor.setNombres(request.nombres());
        doctor.setApellidos(request.apellidos());
        doctor.setCmp(request.cmp());
        doctor.setEspecialidad(request.especialidad());

        doctor = doctorRepository.save(doctor);

        return new DoctorResponse(
                doctor.getId(),
                doctor.getUsuarioId(),
                doctor.getNombres(),
                doctor.getApellidos(),
                doctor.getCmp(),
                doctor.getEspecialidad(),
                doctor.getActivo()
        );
    }

    @Override
    public void eliminar(Long id){
        DoctorEntity doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
        doctor.setActivo(false);
        doctorRepository.save(doctor);
    }

}









