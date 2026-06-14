package com.clinicajesus.controller;

import com.clinicajesus.dto.CitaRequest;
import com.clinicajesus.dto.CitaResponse;
import com.clinicajesus.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(
            CitaService citaService
    ) {
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<CitaResponse> crear(
            @RequestBody CitaRequest request
    ) {
        return ResponseEntity.ok(
                citaService.crear(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<CitaResponse>> listar() {
        return ResponseEntity.ok(
                citaService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                citaService.buscarPorId(id)
        );
    }

}