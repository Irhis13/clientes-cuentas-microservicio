package com.rv.banco.modules.cliente.domain.exception;

import com.rv.banco.common.exceptions.ApiCodeException;

public class ClienteException extends ApiCodeException {

    public static final int ERROR_CODE = 404001;

    public ClienteException(final String message) {
        super(message, ERROR_CODE);
    }
}
