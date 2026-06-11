package com.clinicajesus.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "horarios_disponibles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_horario_doctor_fecha_hora_inicio",
                        columnNames = {
                                "doctor_id",
                                "fecha",
                                "hora_inicio"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioDisponibleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;

    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    private Boolean activo;

    @PrePersist
    public void prePersist() {

        if (activo == null) {
            activo = true;
        }

        if (horaInicio != null && horaFin == null) {
            horaFin = horaInicio.plusMinutes(30);
        }
    }
}