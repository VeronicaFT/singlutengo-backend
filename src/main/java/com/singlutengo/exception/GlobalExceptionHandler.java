package com.singlutengo.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que maneja errores globales de validación.
 * Permite devolver mensajes claros cuando algo no cumple con las reglas de @Valid.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores cuando se violan las validaciones de @Valid.
     *
     * @param ex la excepción que lanza Spring cuando algo no cumple con las anotaciones de validación
     * @return un mapa con los campos y los mensajes de error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
