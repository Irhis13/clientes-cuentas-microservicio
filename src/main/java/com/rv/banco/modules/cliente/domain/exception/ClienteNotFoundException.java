package com.rv.banco.modules.cliente.domain.exception;

import com.rv.banco.common.exceptions.ApiCodeException;

public class ClienteNotFoundException extends ApiCodeException {
    public static final int ERROR_CODE = 404001;
    private static final String MESSAGE = "No se ha encontrado el Cliente con DNI %s";

    public ClienteNotFoundException(final String dni) {
        super(String.format(MESSAGE, dni), ERROR_CODE);
    }
}