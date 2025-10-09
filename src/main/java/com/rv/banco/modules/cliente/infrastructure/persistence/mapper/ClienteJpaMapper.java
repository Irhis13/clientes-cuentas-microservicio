package com.rv.banco.modules.cliente.infrastructure.persistence.mapper;

import com.rv.banco.modules.cliente.domain.model.Cliente;
import com.rv.banco.modules.cliente.infrastructure.persistence.entity.ClienteJpaEntity;
import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;

public final class ClienteJpaMapper {

    private ClienteJpaMapper(){}

    public static Cliente toDomain(ClienteJpaEntity e) {
        var c = new Cliente(e.getDni(), e.getNombre(), e.getApellido1(), e.getApellido2());
        c.setFechaNacimiento(e.getFechaNacimiento());

        if (e.getCuentas() != null) {
            var cuentas = e.getCuentas().stream()
                    .map(cb -> new CuentaBancaria(cb.getId(), cb.getTipoCuenta(), cb.getTotal()))
                    .toList();
            c.setCuentas(cuentas);
        }

        return c;
    }

    public static ClienteJpaEntity toEntity(Cliente d){
        var e = new ClienteJpaEntity();
        e.setDni(d.getDni());
        e.setNombre(d.getNombre());
        e.setApellido1(d.getApellido1());
        e.setApellido2(d.getApellido2());
        e.setFechaNacimiento(d.getFechaNacimiento());
        return e;
    }
}
