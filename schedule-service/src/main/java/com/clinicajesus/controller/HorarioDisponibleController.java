package com.clinicajesus.controller;

import com.clinicajesus.dto.HorarioDisponibleRequest;
import com.clinicajesus.dto.HorarioDisponibleResponse;
import com.clinicajesus.service.HorarioDisponibleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioDisponibleController {

    private final HorarioDisponibleService service;

    public HorarioDisponibleController(
            HorarioDisponibleService service
    ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HorarioDisponibleResponse> crear(
            @RequestBody HorarioDisponibleRequest request
    ) {
        return ResponseEntity.ok(
                service.crear(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<HorarioDisponibleResponse>> listar() {
        return ResponseEntity.ok(
                service.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDisponibleResponse> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                service.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDisponibleResponse> actualizar(
            @PathVariable Long id,
            @RequestBody HorarioDisponibleRequest request
    ) {
        return ResponseEntity.ok(
                service.actualizar(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}