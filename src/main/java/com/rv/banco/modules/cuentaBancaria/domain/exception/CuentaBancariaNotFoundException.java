package com.rv.banco.modules.cuentaBancaria.domain.exception;

public class CuentaBancariaNotFoundException extends RuntimeException {
    private static final String MESSAGE = "No se ha encontrado la cuenta bancaria con id %d";

    public CuentaBancariaNotFoundException(final Long id) {
        super(String.format(MESSAGE, id));
    }
}
