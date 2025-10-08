package com.rv.banco.modules.cliente.domain.exception;

import com.rv.banco.common.exceptions.ApiCodeException;

import java.util.UUID;

public class ClienteNotFoundException extends ApiCodeException {
    public static final int ERROR_CODE = 202;
    private static final String MESSAGE = "No se ha encontrado el Cliente con id %s";

    public ClienteNotFoundException(final UUID id) {
        super(String.format(MESSAGE, id), ERROR_CODE);
    }

    public ClienteNotFoundException(final String msg) {
        super(msg, ERROR_CODE);
    }
}
