package com.clinicajesus.payment_service.service.impl;

import com.clinicajesus.payment_service.client.AppointmentClient;
import com.clinicajesus.payment_service.dto.PagoRequest;
import com.clinicajesus.payment_service.dto.PagoResponse;
import com.clinicajesus.payment_service.entity.PagoEntity;
import com.clinicajesus.payment_service.enums.EstadoPago;
import com.clinicajesus.payment_service.repository.PagoRepository;
import com.clinicajesus.payment_service.service.PagoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final AppointmentClient appointmentClient;

    public PagoServiceImpl(
            PagoRepository pagoRepository,
            AppointmentClient appointmentClient
    ) {
        this.pagoRepository = pagoRepository;
        this.appointmentClient = appointmentClient;
    }

    @Override
    public PagoResponse crear(
            PagoRequest request
    ) {

        PagoEntity pago = PagoEntity.builder()
                .citaId(request.citaId())
                .pacienteId(request.pacienteId())
                .monto(request.monto())
                .metodoPago(request.metodoPago())
                .build();

        pago = pagoRepository.save(pago);

        return mapToResponse(pago);
    }

    @Override
    public List<PagoResponse> listar() {

        return pagoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PagoResponse buscarPorId(Long id) {

        PagoEntity pago = pagoRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Pago no encontrado"
                        )
                );

        return mapToResponse(pago);
    }

    @Override
    public List<PagoResponse> listarPorPaciente(
            Long pacienteId
    ) {

        return pagoRepository.findByPacienteId(
                        pacienteId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PagoResponse mapToResponse(
            PagoEntity pago
    ) {

        return new PagoResponse(
                pago.getId(),
                pago.getCitaId(),
                pago.getPacienteId(),
                pago.getMonto(),
                pago.getMetodoPago(),
                pago.getEstado(),
                pago.getFechaPago()
        );
    }

    @Override
    public PagoResponse cambiarEstado(
            Long id,
            String estado
    ) {

        PagoEntity pago =
                pagoRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Pago no encontrado"
                                )
                        );

        pago.setEstado(
                EstadoPago.valueOf(
                        estado.toUpperCase()
                )
        );

        pago = pagoRepository.save(pago);

        if (
                pago.getEstado() == EstadoPago.PAGADO
        ) {

            appointmentClient.confirmarCita(
                    pago.getCitaId(),
                    "CONFIRMADA"
            );
        }

        return mapToResponse(pago);
    }
}