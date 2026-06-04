package com.clinicajesus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctores")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 20)
    private String cmp;

    @Column(nullable = false, length = 100)
    private String especialidad;

    @Column(nullable = false)
    private Boolean activo;
}