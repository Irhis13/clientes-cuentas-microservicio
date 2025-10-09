package com.rv.banco.modules.cliente.application.repository;

import com.rv.banco.modules.cliente.domain.model.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    List<Cliente> findAll();

    List<Cliente> findByFechaNacimientoBefore(LocalDate maxBirthDate);

    List<Cliente> findClientesConSumaCuentasMayorQue(double cantidad);

    Optional<Cliente> findByDni(String dni);

    Cliente save(Cliente cliente);
}
