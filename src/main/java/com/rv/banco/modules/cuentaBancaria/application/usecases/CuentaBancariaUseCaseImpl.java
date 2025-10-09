package com.rv.banco.modules.cuentaBancaria.application.usecases;

import com.rv.banco.modules.cliente.application.repository.ClienteRepository;
import com.rv.banco.modules.cliente.domain.model.Cliente;
import com.rv.banco.modules.cuentaBancaria.application.repository.CuentaBancariaRepository;
import com.rv.banco.modules.cuentaBancaria.domain.exception.CuentaBancariaException;
import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CuentaBancariaUseCaseImpl implements CuentaBancariaUseCase {

    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public CuentaBancaria create(String dniCliente, String tipoCuenta, Double total) {
        if (dniCliente == null || dniCliente.isBlank()) {
            throw new CuentaBancariaException("dniCliente es obligatorio");
        }
        if (tipoCuenta == null || tipoCuenta.isBlank()) {
            throw new CuentaBancariaException("tipoCuenta es obligatorio");
        }
        if (total == null || total < 0) {
            throw new CuentaBancariaException("total debe ser >= 0");
        }

        var cliente = clienteRepository.findByDni(dniCliente)
                .orElseGet(() -> {
                    var nuevo = new Cliente(dniCliente, null, null, null);
                    return clienteRepository.save(nuevo);
                });

        var nuevaCuenta = new CuentaBancaria(null, tipoCuenta, total);
        return cuentaBancariaRepository.save(cliente.getDni(), nuevaCuenta);
    }

    @Override
    @Transactional
    public void updateTotal(Long idCuenta, Double total) {
        if (idCuenta == null || idCuenta <= 0) {
            throw new CuentaBancariaException("idCuenta es obligatorio y debe ser > 0");
        }
        if (total == null || total < 0) {
            throw new CuentaBancariaException("total debe ser >= 0");
        }
        cuentaBancariaRepository.updateTotal(idCuenta, total);
    }
}