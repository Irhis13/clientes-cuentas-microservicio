package com.rv.banco.modules.cuentaBancaria.infrastructure.rest.response;

public record CuentaBancariaRestResponse(
        Long id,
        String tipoCuenta,
        Double total
) {}
