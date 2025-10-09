package com.rv.banco.common.seeders;

import com.rv.banco.modules.cliente.infrastructure.persistence.entity.ClienteJpaEntity;
import com.rv.banco.modules.cliente.infrastructure.persistence.repository.ClienteJpaRepository;
import com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.entity.CuentaBancariaJpaEntity;
import com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.repository.CuentaBancariaJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder {
    private final ClienteJpaRepository clienteJpaRepository;
    private final CuentaBancariaJpaRepository cuentaJpaRepository;

    private static final String PREMIUM = "PREMIUM";
    private static final String NORMAL  = "NORMAL";
    private static final String JUNIOR  = "JUNIOR";

    @PostConstruct
    public void seed() {
        if (clienteJpaRepository.count() > 0) return;

        var clientes = List.of(
                new ClienteJpaEntity("11111111A","Juan","Pérez","López", LocalDate.of(1959,9,12), List.of()),
                new ClienteJpaEntity("22222222B","Raúl","Canales","Rodríguez", LocalDate.of(1985,3,1), List.of()),
                new ClienteJpaEntity("33333333C","Elena","Ruiz","Herrera", LocalDate.of(2010,5,10), List.of()),
                new ClienteJpaEntity("44444444D","Raquel","Ruiz","Herrera", LocalDate.of(2002,6,21), List.of()),
                new ClienteJpaEntity("55555555E","María","Sánchez","Torres", LocalDate.of(1999,8,8), List.of())
        );
        clienteJpaRepository.saveAll(clientes);

        var cuentas = List.of(
                new CuentaBancariaJpaEntity(null, PREMIUM, 150000.0, clientes.get(0)),
                new CuentaBancariaJpaEntity(null, NORMAL, 20000.0, clientes.get(0)),
                new CuentaBancariaJpaEntity(null, NORMAL, 50000.0, clientes.get(1)),
                new CuentaBancariaJpaEntity(null, JUNIOR, 300.0, clientes.get(1)),
                new CuentaBancariaJpaEntity(null, JUNIOR, 300.0, clientes.get(2)),
                new CuentaBancariaJpaEntity(null, NORMAL, 75000.0, clientes.get(3)),
                new CuentaBancariaJpaEntity(null, PREMIUM, 120000.0, clientes.get(4))
        );
        cuentaJpaRepository.saveAll(cuentas);
    }
}
