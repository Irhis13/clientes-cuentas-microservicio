package com.rv.banco.modules.cliente.infrastructure.persistence.repository;

import com.rv.banco.modules.cliente.application.repository.ClienteRepository;
import com.rv.banco.modules.cliente.domain.model.Cliente;
import com.rv.banco.modules.cliente.infrastructure.persistence.mapper.ClienteJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteJpaRepository jpa;

    @Override
    public List<Cliente> findAll() {
        return jpa.findAll().stream().map(ClienteJpaMapper::toDomain).toList();
    }

    @Override
    public List<Cliente> findByFechaNacimientoBefore(LocalDate maxBirthDate) {
        return jpa.findByFechaNacimientoBefore(maxBirthDate).stream()
                .map(ClienteJpaMapper::toDomain).toList();
    }

    @Override
    public List<Cliente> findClientesConSumaCuentasMayorQue(double cantidad) {
        return jpa.findConSumaCuentasMayorQue(cantidad).stream()
                .map(ClienteJpaMapper::toDomain).toList();
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        return jpa.findById(dni).map(ClienteJpaMapper::toDomain);
    }

    @Override
    public Cliente save(Cliente cliente) {
        var saved = jpa.save(ClienteJpaMapper.toEntity(cliente));
        return ClienteJpaMapper.toDomain(saved);
    }
}
