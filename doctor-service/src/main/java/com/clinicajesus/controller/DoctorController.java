package com.clinicajesus.controller;

import com.clinicajesus.dto.DoctorRequest;
import com.clinicajesus.dto.DoctorResponse;
import com.clinicajesus.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(
            DoctorService doctorService
    ) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> crear(
            @RequestBody DoctorRequest request
    ) {
        return ResponseEntity.ok(
                doctorService.crear(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> listar() {
        return ResponseEntity.ok(
                doctorService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> buscarPorId(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                doctorService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> actualizar(
            @PathVariable Long id,
            @RequestBody DoctorRequest request
    ){
        return ResponseEntity.ok(
                doctorService.actualizar(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ){
        doctorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }



}