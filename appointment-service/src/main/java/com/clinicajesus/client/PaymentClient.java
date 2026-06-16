package com.clinicajesus.client;

import com.clinicajesus.dto.PagoRequest;
import com.clinicajesus.dto.PagoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/api/pagos")
    PagoResponse crearPago(
            @RequestBody PagoRequest request
    );
}