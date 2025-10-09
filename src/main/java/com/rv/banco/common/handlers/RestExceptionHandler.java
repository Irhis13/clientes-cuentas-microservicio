package com.rv.banco.common.handlers;

import com.rv.banco.common.exceptions.ApiCodeException;
import com.rv.banco.common.models.ApiCodeError;
import com.rv.banco.common.models.ApiParamErrorField;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ApiCodeException.class)
    public ResponseEntity<ApiCodeError> handleApiCodeException(final ApiCodeException ex) {
        final HttpStatus status = mapStatus(ex.getErrorCode());
        return ResponseEntity.status(status)
                .body(new ApiCodeError(ex.getErrorCode(), ex.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiCodeError> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        final List<ApiParamErrorField> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toParamError)
                .toList();
        return ResponseEntity.badRequest()
                .body(new ApiCodeError(400000, "Bad Request", fields));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiCodeError> handleConstraintViolation(final ConstraintViolationException ex) {
        final List<ApiParamErrorField> fields = ex.getConstraintViolations()
                .stream()
                .map(this::toParamError)
                .toList();
        return ResponseEntity.badRequest()
                .body(new ApiCodeError(400000, "Bad Request", fields));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiCodeError> handleTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiCodeError(400000, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiCodeError> handleGeneric(final Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiCodeError(500000, "Unexpected error", null));
    }

    // helpers
    private ApiParamErrorField toParamError(final FieldError fe) {
        return new ApiParamErrorField(fe.getField(), fe.getDefaultMessage());
    }

    private ApiParamErrorField toParamError(final ConstraintViolation<?> cv) {
        final String path = cv.getPropertyPath() != null ? cv.getPropertyPath().toString() : "";
        final String field = path.contains(".") ? path.substring(path.lastIndexOf('.') + 1) : path;
        return new ApiParamErrorField(field, cv.getMessage());
    }

    private HttpStatus mapStatus(final Integer code) {
        if (code == null) return HttpStatus.BAD_REQUEST;
        if (code >= 404000 && code < 405000) return HttpStatus.NOT_FOUND;
        if (code >= 400000 && code < 401000) return HttpStatus.BAD_REQUEST;
        if (code >= 500000 && code < 501000) return HttpStatus.INTERNAL_SERVER_ERROR;
        return HttpStatus.BAD_REQUEST;
    }
}