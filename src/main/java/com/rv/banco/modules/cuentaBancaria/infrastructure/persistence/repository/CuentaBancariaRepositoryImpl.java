package com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.repository;

import com.rv.banco.modules.cliente.infrastructure.persistence.repository.ClienteJpaRepository;
import com.rv.banco.modules.cuentaBancaria.application.repository.CuentaBancariaRepository;
import com.rv.banco.modules.cuentaBancaria.domain.exception.CuentaBancariaNotFoundException;
import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;
import com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.mapper.CuentaBancariaJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CuentaBancariaRepositoryImpl implements CuentaBancariaRepository {

    private final CuentaBancariaJpaRepository cuentasJpa;
    private final ClienteJpaRepository clienteJpa;

    @Override
    public CuentaBancaria save(String dniCliente, CuentaBancaria cuenta) {
        var cliente = clienteJpa.findById(dniCliente).orElseGet(() -> {
            var nuevo = new com.rv.banco.modules.cliente.infrastructure.persistence.entity.ClienteJpaEntity();
            nuevo.setDni(dniCliente);
            return clienteJpa.save(nuevo);
        });

        var toSave = CuentaBancariaJpaMapper.toEntity(cuenta, cliente);
        var saved = cuentasJpa.save(toSave);
        return CuentaBancariaJpaMapper.toDomain(saved);
    }

    @Override
    public Optional<CuentaBancaria> findById(Long id) {
        return cuentasJpa.findById(id).map(CuentaBancariaJpaMapper::toDomain);
    }

    @Override
    public CuentaBancaria updateTotal(Long id, double total) {
        var entity = cuentasJpa.findById(id)
                .orElseThrow(() -> new CuentaBancariaNotFoundException(id));
        entity.setTotal(total);
        var saved = cuentasJpa.save(entity);
        return CuentaBancariaJpaMapper.toDomain(saved);
    }
}
