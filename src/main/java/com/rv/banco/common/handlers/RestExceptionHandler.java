package com.rv.banco.common.handlers;

import com.rv.banco.common.models.ApiCodeError;
import com.rv.banco.common.models.ApiParamErrorField;
import com.rv.banco.modules.cliente.domain.exception.ClienteException;
import com.rv.banco.modules.cliente.domain.exception.ClienteNotFoundException;
import com.rv.banco.modules.cuentaBancaria.domain.exception.CuentaBancariaException;
import com.rv.banco.modules.cuentaBancaria.domain.exception.CuentaBancariaNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiCodeError> handleValidation(MethodArgumentNotValidException ex) {
        var detalles = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(this::toParamError)
                .toList();

        var body = new ApiCodeError(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación en los datos enviados",
                detalles
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiCodeError> handleConstraintViolation(ConstraintViolationException ex) {
        var detalles = ex.getConstraintViolations()
                .stream()
                .map(this::toParamError)
                .toList();

        var body = new ApiCodeError(
                HttpStatus.BAD_REQUEST.value(),
                "Los parámetros enviados no son válidos",
                detalles
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiCodeError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        var detalle = new ApiParamErrorField(ex.getName(), "Tipo de dato incorrecto");
        var body = new ApiCodeError(
                HttpStatus.BAD_REQUEST.value(),
                "Parámetros inválidos en la solicitud",
                List.of(detalle)
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler({ClienteException.class, CuentaBancariaException.class})
    public ResponseEntity<ApiCodeError> handleBusinessBadRequest(RuntimeException ex) {
        var body = new ApiCodeError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler({ClienteNotFoundException.class, CuentaBancariaNotFoundException.class})
    public ResponseEntity<ApiCodeError> handleBusinessNotFound(RuntimeException ex) {
        var body = new ApiCodeError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiCodeError> handleNoResourceFound(NoResourceFoundException ignored) {
        var body = new ApiCodeError(
                HttpStatus.NOT_FOUND.value(),
                "El recurso solicitado no existe",
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiCodeError> handleGeneric(Exception ignored) {
        var body = new ApiCodeError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private ApiParamErrorField toParamError(FieldError fe) {
        return new ApiParamErrorField(fe.getField(), fe.getDefaultMessage());
    }

    private ApiParamErrorField toParamError(ConstraintViolation<?> cv) {
        String field = cv.getPropertyPath().toString();
        return new ApiParamErrorField(field, cv.getMessage());
    }
}