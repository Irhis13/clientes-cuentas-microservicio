package com.rv.banco.modules.cliente.infrastructure.rest.response;

import com.rv.banco.modules.cuentaBancaria.infrastructure.rest.response.CuentaBancariaRestResponse;

import java.time.LocalDate;
import java.util.List;

public record ClienteRestResponse(
        String dni,
        String nombre,
        String apellido1,
        String apellido2,
        LocalDate fechaNacimiento,
        List<CuentaBancariaRestResponse> cuentas
) {}
