package com.rv.banco.modules.cuentaBancaria.infrastructure.rest.mapper;

import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;
import com.rv.banco.modules.cuentaBancaria.infrastructure.rest.response.CuentaBancariaRestResponse;

public final class CuentaBancariaRestMapper {
    private CuentaBancariaRestMapper(){}

    public static CuentaBancariaRestResponse toResponse(CuentaBancaria c) {
        return new CuentaBancariaRestResponse(
                c.getId(),
                c.getTipoCuenta(),
                c.getTotal()
        );
    }
}
