package com.clinicajesus.service.impl;

import com.clinicajesus.client.AuthClient;
import com.clinicajesus.client.ScheduleClient;
import com.clinicajesus.dto.*;
import com.clinicajesus.entity.CitaEntity;
import com.clinicajesus.enums.EstadoCita;
import com.clinicajesus.repository.CitaRepository;
import com.clinicajesus.service.CitaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final AuthClient authClient;
    private final ScheduleClient scheduleClient;

    public CitaServiceImpl(
            CitaRepository citaRepository,
            AuthClient authClient,
            ScheduleClient scheduleClient
    ) {
        this.citaRepository = citaRepository;
        this.authClient = authClient;
        this.scheduleClient = scheduleClient;
    }

    @Override
    public CitaResponse crear(CitaRequest request) {

        UsuarioResponse paciente =
                authClient.obtenerUsuario(
                        request.pacienteId()
                );

        if (!paciente.activo()) {
            throw new RuntimeException(
                    "Paciente inactivo"
            );
        }

        if (!paciente.rol().equals("PACIENTE")) {
            throw new RuntimeException(
                    "El usuario no tiene rol PACIENTE"
            );
        }

        HorarioDisponibleResponse horario =
                scheduleClient.obtenerHorario(
                        request.horarioDisponibleId()
                );

        if (!horario.activo()) {
            throw new RuntimeException(
                    "Horario inactivo"
            );
        }

        LocalDateTime fechaHoraHorario =
                LocalDateTime.of(
                        horario.fecha(),
                        horario.horaInicio()
                );

        if (
                fechaHoraHorario.isBefore(
                        LocalDateTime.now()
                )
        ) {
            throw new RuntimeException(
                    "No se puede reservar una cita en el pasado"
            );
        }

        boolean ocupado =
                citaRepository
                        .existsByHorarioDisponibleIdAndEstadoIn(
                                request.horarioDisponibleId(),
                                List.of(
                                        EstadoCita.PENDIENTE,
                                        EstadoCita.CONFIRMADA
                                )
                        );

        if (ocupado) {
            throw new RuntimeException(
                    "Horario ya reservado"
            );
        }

        CitaEntity cita =
                CitaEntity.builder()
                        .pacienteId(request.pacienteId())
                        .doctorId(horario.doctorId()) // si viene del horario
                        .horarioDisponibleId(request.horarioDisponibleId())
                        .precio(BigDecimal.ZERO)
                        .moneda("PEN")
                        .motivoConsulta(request.motivoConsulta())
                        .observaciones(null)
                        .build();

        cita = citaRepository.save(cita);

        return mapToResponse(cita);
    }

    @Override
    public List<CitaResponse> listar() {

        return citaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CitaResponse buscarPorId(Long id) {

        CitaEntity cita =
                citaRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Cita no encontrada"
                                )
                        );

        return mapToResponse(cita);
    }

    private CitaResponse mapToResponse(
            CitaEntity cita
    ) {

        return new CitaResponse(
                cita.getId(),
                cita.getPacienteId(),
                cita.getHorarioDisponibleId(),
                cita.getMotivoConsulta(),
                cita.getEstado(),
                cita.getFechaHoraCreacion()
        );
    }
}