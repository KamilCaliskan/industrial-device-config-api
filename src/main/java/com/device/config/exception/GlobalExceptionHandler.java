package com.device.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");

        // Extract field names and their corresponding validation messages
        Map<String, String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null ? 
                                      fieldError.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing // Keep the first error if a field has multiple
                ));

        body.put("errors", validationErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(org.springframework.dao.DataIntegrityViolationException ex) {
        Map<String, Object> body = new java.util.LinkedHashMap<>();
        body.put("timestamp", java.time.LocalDateTime.now());
        body.put("status", org.springframework.http.HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        
        // Default generic message
        String message = "A resource with this unique value already exists.";
        
        // Inspect the root cause to confirm it originates from the duplicate name constraint
        String specificCause = ex.getMostSpecificCause().getMessage();
        if (specificCause != null && specificCause.toLowerCase().contains("name")) {
            message = "Device type name already exists";
        }
        
        body.put("message", message);

        return new ResponseEntity<>(body, org.springframework.http.HttpStatus.CONFLICT);
    }


}
