package com.rv.banco.modules.cliente.application.usecases;

import com.rv.banco.modules.cliente.application.repository.ClienteRepository;
import com.rv.banco.modules.cliente.domain.model.Cliente;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteUseCases {

    private final ClienteRepository repo;

    public ClienteUseCases(ClienteRepository repo) { this.repo = repo; }

    public List<Cliente> getAll() { return repo.findAll(); }

    public List<Cliente> getMayoresDeEdad() {
        return repo.findByFechaNacimientoBefore(LocalDate.now().minusYears(18));
    }

    public List<Cliente> getConSumaCuentasSuperiorA(double cantidad) {
        return repo.findClientesConSumaCuentasMayorQue(cantidad);
    }

    public Cliente getByDni(String dni) {
        return repo.findByDni(dni).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}
