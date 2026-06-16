package com.clinicajesus.payment_service.controller;

import com.clinicajesus.payment_service.dto.PagoRequest;
import com.clinicajesus.payment_service.dto.PagoResponse;
import com.clinicajesus.payment_service.service.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(
            PagoService pagoService
    ) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<PagoResponse> crear(
            @RequestBody PagoRequest request
    ) {

        return ResponseEntity.ok(
                pagoService.crear(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<PagoResponse>> listar() {

        return ResponseEntity.ok(
                pagoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponse> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                pagoService.buscarPorId(id)
        );
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<PagoResponse>>
    listarPorPaciente(
            @PathVariable Long pacienteId
    ) {

        return ResponseEntity.ok(
                pagoService.listarPorPaciente(
                        pacienteId
                )
        );
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<PagoResponse> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado
    ) {

        return ResponseEntity.ok(
                pagoService.cambiarEstado(
                        id,
                        estado
                )
        );
    }

    @GetMapping("/{id}/comprobante")
    public ResponseEntity<byte[]> descargarComprobante(
            @PathVariable Long id
    ) {

        byte[] pdf =
                pagoService.descargarComprobante(
                        id
                );

        return ResponseEntity.ok()
                .header(
                        "Content-Disposition",
                        "attachment; filename=comprobante.pdf"
                )
                .header(
                        "Content-Type",
                        "application/pdf"
                )
                .body(pdf);
    }
}