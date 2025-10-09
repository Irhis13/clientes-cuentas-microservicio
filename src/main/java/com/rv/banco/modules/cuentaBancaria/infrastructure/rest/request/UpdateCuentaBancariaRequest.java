package com.rv.banco.modules.cuentaBancaria.infrastructure.rest.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateCuentaBancariaRequest(
        @NotNull @PositiveOrZero Double total
) {}
