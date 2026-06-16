package com.clinicajesus.payment_service.pdf;

import com.clinicajesus.payment_service.entity.PagoEntity;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    public byte[] generarComprobante(
            PagoEntity pago
    ) {

        try {

            Document document =
                    new Document();

            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();

            PdfWriter.getInstance(
                    document,
                    output
            );

            document.open();

            document.add(
                    new Paragraph(
                            "CLINICA JESUS"
                    )
            );

            document.add(
                    new Paragraph(
                            " "
                    )
            );

            document.add(
                    new Paragraph(
                            "COMPROBANTE DE PAGO"
                    )
            );

            document.add(
                    new Paragraph(
                            " "
                    )
            );

            document.add(
                    new Paragraph(
                            "Pago N°: " + pago.getId()
                    )
            );

            document.add(
                    new Paragraph(
                            "Cita ID: " + pago.getCitaId()
                    )
            );

            document.add(
                    new Paragraph(
                            "Paciente ID: " + pago.getPacienteId()
                    )
            );

            document.add(
                    new Paragraph(
                            "Monto: S/. " + pago.getMonto()
                    )
            );

            document.add(
                    new Paragraph(
                            "Método: " + pago.getMetodoPago()
                    )
            );

            document.add(
                    new Paragraph(
                            "Estado: " + pago.getEstado()
                    )
            );

            document.add(
                    new Paragraph(
                            "Fecha: " + pago.getFechaPago()
                    )
            );

            document.close();

            return output.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error generando PDF"
            );
        }
    }
}