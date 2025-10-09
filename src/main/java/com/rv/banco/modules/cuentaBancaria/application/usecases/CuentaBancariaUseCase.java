package com.rv.banco.modules.cuentaBancaria.application.usecases;

import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;

public interface CuentaBancariaUseCase {

    CuentaBancaria create(String dniCliente, String tipoCuenta, Double total);

    void updateTotal(Long idCuenta, Double total);
}
