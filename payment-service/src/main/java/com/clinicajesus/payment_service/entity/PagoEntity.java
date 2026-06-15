package com.clinicajesus.payment_service.entity;

import com.clinicajesus.payment_service.enums.EstadoPago;
import com.clinicajesus.payment_service.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long citaId;

    private Long pacienteId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago estado;

    @Column(nullable = false)
    private LocalDateTime fechaPago;

    @PrePersist
    public void prePersist() {

        if (estado == null) {
            estado = EstadoPago.PENDIENTE;
        }

        if (fechaPago == null) {
            fechaPago = LocalDateTime.now();
        }
    }
}