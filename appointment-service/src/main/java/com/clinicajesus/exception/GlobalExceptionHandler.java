package com.clinicajesus.exception;

import com.clinicajesus.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>
    handleValidation(
            MethodArgumentNotValidException ex
    ){

        String mensaje =
                ex.getBindingResult()
                        .getFieldError()
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
