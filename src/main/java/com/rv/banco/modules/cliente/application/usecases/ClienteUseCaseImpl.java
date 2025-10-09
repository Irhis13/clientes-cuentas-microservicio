package com.rv.banco.modules.cliente.application.usecases;

import com.rv.banco.modules.cliente.application.repository.ClienteRepository;
import com.rv.banco.modules.cliente.domain.exception.ClienteException;
import com.rv.banco.modules.cliente.domain.exception.ClienteNotFoundException;
import com.rv.banco.modules.cliente.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteUseCaseImpl implements ClienteUseCases {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> getMayoresDeEdad() {
        return clienteRepository.findByFechaNacimientoBefore(LocalDate.now().minusYears(18));
    }

    @Override
    public List<Cliente> getConCuentasSuperiorA(double cantidad) {
        if (cantidad < 0) {
            throw new ClienteException("La cantidad debe ser mayor o igual que 0");
        }
        return clienteRepository.findClientesConSumaCuentasMayorQue(cantidad);
    }

    @Override
    public Cliente getCliente(String dni) {
        if (dni == null || dni.isBlank()) {
            throw new ClienteException("El DNI es obligatorio");
        }
        return clienteRepository.findByDni(dni)
                .orElseThrow(() -> new ClienteNotFoundException(dni)); // 404001
    }
}
