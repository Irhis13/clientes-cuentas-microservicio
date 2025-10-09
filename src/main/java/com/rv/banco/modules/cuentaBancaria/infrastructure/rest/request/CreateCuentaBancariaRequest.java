package com.rv.banco.modules.cuentaBancaria.infrastructure.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateCuentaBancariaRequest(
        @NotBlank String dniCliente,
        @NotBlank String tipoCuenta,
        @NotNull @PositiveOrZero Double total
) {}
