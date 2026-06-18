package com.clinicajesus.payment_service.service.impl;

import com.clinicajesus.payment_service.client.AppointmentClient;
import com.clinicajesus.payment_service.client.AuthClient;
import com.clinicajesus.payment_service.client.NotificationClient;
import com.clinicajesus.payment_service.dto.*;
import com.clinicajesus.payment_service.entity.PagoEntity;
import com.clinicajesus.payment_service.enums.EstadoPago;
import com.clinicajesus.payment_service.pdf.PdfGeneratorService;
import com.clinicajesus.payment_service.repository.PagoRepository;
import com.clinicajesus.payment_service.service.PagoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final AppointmentClient appointmentClient;
    private final AuthClient authClient;
    private final NotificationClient notificationClient;
    private final PdfGeneratorService pdfGeneratorService;


    public PagoServiceImpl(
            PagoRepository pagoRepository,
            AppointmentClient appointmentClient,
            AuthClient authClient,
            NotificationClient notificationClient,
            PdfGeneratorService pdfGeneratorService
    ) {
        this.pagoRepository = pagoRepository;
        this.appointmentClient = appointmentClient;
        this.authClient = authClient;
        this.notificationClient = notificationClient;
        this.pdfGeneratorService = pdfGeneratorService;
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

            UsuarioResponse paciente =
                    authClient.obtenerUsuario(
                            pago.getPacienteId()
                    );

            byte[] pdf =
                    pdfGeneratorService.generarComprobante(
                            pago
                    );

            notificationClient.enviarCorreoConAdjunto(

                    new EmailAdjuntoRequest(

                            paciente.email(),

                            "Pago confirmado",

                            "Hola "
                                    + paciente.nombres()
                                    + ", su pago fue registrado correctamente. "
                                    + "Adjuntamos el comprobante de pago.",

                            pdf,

                            "comprobante.pdf"
                    )
            );
        }

        return mapToResponse(pago);
    }

    @Override
    public byte[] descargarComprobante(
            Long pagoId
    ) {

        PagoEntity pago =
                pagoRepository.findById(pagoId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Pago no encontrado"
                                )
                        );

        return pdfGeneratorService
                .generarComprobante(
                        pago
                );
    }
}