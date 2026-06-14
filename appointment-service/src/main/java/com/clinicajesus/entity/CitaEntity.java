package com.clinicajesus.entity;

import com.clinicajesus.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pacienteId;

    private Long doctorId;

    private Long horarioDisponibleId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(length = 3, nullable = false)
    private String moneda;

    @Column(nullable = false, length = 500)
    private String motivoConsulta;

    @Column(length = 1000)
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado;

    @Column(nullable = false)
    private LocalDateTime fechaHoraCreacion;

    @PrePersist
    public void prePersist() {

        if (estado == null) {
            estado = EstadoCita.PENDIENTE;
        }

        if (fechaHoraCreacion == null) {
            fechaHoraCreacion = LocalDateTime.now();
        }

        if (moneda == null) {
            moneda = "PEN";
        }

        if (precio == null) {
            precio = BigDecimal.ZERO;
        }
    }
}