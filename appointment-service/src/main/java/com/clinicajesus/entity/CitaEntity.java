package com.clinicajesus.entity;

import com.clinicajesus.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.*;

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

    private Long horarioDisponibleId;

    @Column(nullable = false, length = 500)
    private String motivoConsulta;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private LocalDateTime fechaHoraCreacion;

    @PrePersist
    public void prePersist() {

        if (fechaHoraCreacion == null) {
            fechaHoraCreacion = LocalDateTime.now();
        }

        if (estado == null) {
            estado = EstadoCita.PENDIENTE;
        }
    }
}