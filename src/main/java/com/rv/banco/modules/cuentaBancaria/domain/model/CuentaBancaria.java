package com.rv.banco.modules.cuentaBancaria.domain.model;

import lombok.Getter;

@Getter
public class CuentaBancaria {

    private Long id;
    private String tipoCuenta;
    private Double total;

    public CuentaBancaria(
            Long id,
            String tipoCuenta,
            Double total
    ) {
        setId(id);
        setTipoCuenta(tipoCuenta);
        setTotal(total);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
