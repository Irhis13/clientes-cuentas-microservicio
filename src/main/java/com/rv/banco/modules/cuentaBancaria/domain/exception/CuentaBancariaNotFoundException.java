package com.rv.banco.modules.cuentaBancaria.domain.exception;

import com.rv.banco.common.exceptions.ApiCodeException;

public class CuentaBancariaNotFoundException extends ApiCodeException {

    public static final int ERROR_CODE = 404002;
    private static final String MESSAGE = "No se ha encontrado la cuenta bancaria con id %d";

    public CuentaBancariaNotFoundException(final Long id) {
        super(String.format(MESSAGE, id), ERROR_CODE);
    }
}
