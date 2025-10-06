package com.rv.banco.modules.cliente.infrastructure.rest.mapper;

import com.rv.banco.modules.cliente.domain.model.Cliente;
import com.rv.banco.modules.cliente.infrastructure.rest.response.ClienteRestResponse;
import com.rv.banco.modules.cuentaBancaria.infrastructure.rest.response.CuentaBancariaRestResponse;

import java.util.List;

public final class ClienteRestMapper {
    private ClienteRestMapper(){}

    public static ClienteRestResponse toResponse(Cliente c){
        var cuentas = c.getCuentas()==null ? List.<CuentaBancariaRestResponse>of()
                : c.getCuentas().stream()
                .map(k -> new CuentaBancariaRestResponse(k.getId(), k.getTipoCuenta(), k.getTotal()))
                .toList();
        return new ClienteRestResponse(
                c.getDni(), c.getNombre(), c.getApellido1(), c.getApellido2(),
                c.getFechaNacimiento(), cuentas
        );
    }
}
