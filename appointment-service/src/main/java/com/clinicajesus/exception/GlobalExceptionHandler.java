package com.clinicajesus.exception;

import com.clinicajesus.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ErrorResponse>
        handleRuntimeException(
                RuntimeException ex
        ) {

            return ResponseEntity.badRequest()
                    .body(
                            new ErrorResponse(
                                    ex.getMessage(),
                                    LocalDateTime.now()
                            )
                    );
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse>
        handleValidation(
                MethodArgumentNotValidException ex
        ) {

            String mensaje =
                    ex.getBindingResult()
                            .getFieldErrors()
                            .get(0)
                            .getDefaultMessage();

            return ResponseEntity.badRequest()
                    .body(
                            new ErrorResponse(
                                    mensaje,
                                    LocalDateTime.now()
                            )
                    );
        }
}
