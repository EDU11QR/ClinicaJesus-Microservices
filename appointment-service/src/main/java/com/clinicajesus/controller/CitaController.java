package com.clinicajesus.controller;

import com.clinicajesus.dto.CitaRequest;
import com.clinicajesus.dto.CitaResponse;
import com.clinicajesus.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/por-paciente")
    public ResponseEntity<List<CitaResponse>>listarPorPaciente(
            @RequestParam Long pacienteId
    ){
        return ResponseEntity.ok(
                citaService.listarPorPaciente(pacienteId)
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

    @PutMapping("/{id}/estado")
    public ResponseEntity<CitaResponse> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado
    ) {

        return ResponseEntity.ok(
                citaService.cambiarEstado(
                        id,
                        estado
                )
        );
    }


    @GetMapping("/por-doctor")
    public ResponseEntity<List<CitaResponse>>
    listarPorDoctor(
            @RequestParam Long doctorId
    ) {

        return ResponseEntity.ok(
                citaService.listarPorDoctor(
                        doctorId
                )
        );
    }


    @GetMapping("/por-doctor-y-fecha")
    public ResponseEntity<List<CitaResponse>>
    listarPorDoctorYFecha(
            @RequestParam Long doctorId,
            @RequestParam LocalDate fecha
    ) {

        return ResponseEntity.ok(
                citaService.listarPorDoctorYFecha(
                        doctorId,
                        fecha
                )
        );
    }

}