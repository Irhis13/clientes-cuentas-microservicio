package com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.repository;

import com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.entity.CuentaBancariaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaBancariaJpaRepository extends JpaRepository<CuentaBancariaJpaEntity, Long> {
}
