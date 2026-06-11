package com.clinicajesus.service.impl;

import com.clinicajesus.client.DoctorClient;
import com.clinicajesus.dto.DoctorResponse;
import com.clinicajesus.dto.HorarioDisponibleRequest;
import com.clinicajesus.dto.HorarioDisponibleResponse;
import com.clinicajesus.entity.HorarioDisponibleEntity;
import com.clinicajesus.repository.HorarioDisponibleRepository;
import com.clinicajesus.service.HorarioDisponibleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioDisponibleServiceImpl
        implements HorarioDisponibleService {

    private final HorarioDisponibleRepository repository;
    private final DoctorClient doctorClient;

    public HorarioDisponibleServiceImpl(
            HorarioDisponibleRepository repository,
            DoctorClient doctorClient
    ) {
        this.repository = repository;
        this.doctorClient = doctorClient;
    }

    @Override
    public HorarioDisponibleResponse crear(
            HorarioDisponibleRequest request
    ) {

        DoctorResponse doctor =
                doctorClient.obtenerDoctor(
                        request.doctorId()
                );

        if (
                repository.existsByDoctorIdAndFechaAndHoraInicio(
                        request.doctorId(),
                        request.fecha(),
                        request.horaInicio()
                )
        ) {
            throw new RuntimeException(
                    "Ya existe un horario registrado para ese doctor"
            );
        }

        HorarioDisponibleEntity horario =
                HorarioDisponibleEntity.builder()
                        .doctorId(request.doctorId())
                        .fecha(request.fecha())
                        .horaInicio(request.horaInicio())
                        .horaFin(
                                request.horaInicio().plusMinutes(30)
                        )
                        .activo(true)
                        .build();

        horario = repository.save(horario);

        return mapToResponse(horario);
    }

    @Override
    public List<HorarioDisponibleResponse> listar() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public HorarioDisponibleResponse buscarPorId(Long id) {

        HorarioDisponibleEntity horario =
                repository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Horario no encontrado"
                                )
                        );

        return mapToResponse(horario);
    }

    @Override
    public HorarioDisponibleResponse actualizar(
            Long id,
            HorarioDisponibleRequest request
    ) {

        HorarioDisponibleEntity horario =
                repository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Horario no encontrado"
                                )
                        );

        horario.setDoctorId(request.doctorId());
        horario.setFecha(request.fecha());
        horario.setHoraInicio(request.horaInicio());
        horario.setHoraFin(
                request.horaInicio().plusMinutes(30)
        );

        horario = repository.save(horario);

        return mapToResponse(horario);
    }

    @Override
    public void eliminar(Long id) {

        HorarioDisponibleEntity horario =
                repository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Horario no encontrado"
                                )
                        );

        horario.setActivo(false);

        repository.save(horario);
    }

    private HorarioDisponibleResponse mapToResponse(
            HorarioDisponibleEntity horario
    ) {

        return new HorarioDisponibleResponse(
                horario.getId(),
                horario.getDoctorId(),
                horario.getFecha(),
                horario.getHoraInicio(),
                horario.getHoraFin(),
                horario.getActivo()
        );
    }
}