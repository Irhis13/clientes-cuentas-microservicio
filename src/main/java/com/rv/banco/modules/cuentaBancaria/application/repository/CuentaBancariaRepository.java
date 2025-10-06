package com.rv.banco.modules.cuentaBancaria.application.repository;

import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;
import java.util.Optional;

public interface CuentaBancariaRepository {

    CuentaBancaria save(String dniCliente, CuentaBancaria cuenta);

    Optional<CuentaBancaria> findById(Long id);

    CuentaBancaria updateTotal(Long id, double total);
}
