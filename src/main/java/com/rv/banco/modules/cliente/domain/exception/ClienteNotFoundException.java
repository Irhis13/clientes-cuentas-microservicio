package com.rv.banco.modules.cliente.domain.exception;

public class ClienteNotFoundException extends RuntimeException {
    private static final String MESSAGE = "No se ha encontrado el cliente con DNI %s";

    public ClienteNotFoundException(final String dni) {
        super(String.format(MESSAGE, dni));
    }
}