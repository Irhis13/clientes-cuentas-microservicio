package com.rv.banco.modules.cliente.application.repository;

import com.rv.banco.modules.cliente.domain.model.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    // Lista de todos los clientes
    List<Cliente> findAll();

    // Clientes mayores de 18 anyos
    List<Cliente> findByFechaNacimientoBefore(LocalDate maxBirthDate);

    // Clientes con cuentas que superen cierta cantidad
    List<Cliente> findClientesConSumaCuentasMayorQue(double cantidad);

    Optional<Cliente> findByDni(String dni);

    Cliente save(Cliente cliente);
}
