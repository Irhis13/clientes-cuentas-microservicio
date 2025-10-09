package com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.mapper;

import com.rv.banco.modules.cliente.infrastructure.persistence.entity.ClienteJpaEntity;
import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;
import com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.entity.CuentaBancariaJpaEntity;

public final class CuentaBancariaJpaMapper {
    private CuentaBancariaJpaMapper(){}

    public static CuentaBancaria toDomain(CuentaBancariaJpaEntity e){
        if (e == null) return null;
        return new CuentaBancaria(e.getId(), e.getTipoCuenta(), e.getTotal());
    }

    public static CuentaBancariaJpaEntity toEntity(CuentaBancaria d, ClienteJpaEntity cliente){
        if (d == null) return null;
        var e = new CuentaBancariaJpaEntity();
        e.setId(d.getId());
        e.setTipoCuenta(d.getTipoCuenta());
        e.setTotal(d.getTotal());
        e.setCliente(cliente);
        return e;
    }
}
