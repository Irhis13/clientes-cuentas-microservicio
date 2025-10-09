package com.rv.banco.modules.cliente.infrastructure.persistence.repository;

import com.rv.banco.modules.cliente.infrastructure.persistence.entity.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ClienteJpaRepository extends JpaRepository<ClienteJpaEntity, String> {

    List<ClienteJpaEntity> findByFechaNacimientoBefore(LocalDate maxDate);

    @Query("""
       select c from ClienteJpaEntity c
       join c.cuentas cb
       group by c
       having sum(cb.total) > :cantidad
    """)
    List<ClienteJpaEntity> findConSumaCuentasMayorQue(double cantidad);
}
