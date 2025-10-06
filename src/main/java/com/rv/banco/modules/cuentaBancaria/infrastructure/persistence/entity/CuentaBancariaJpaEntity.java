package com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.entity;

import com.rv.banco.modules.cliente.infrastructure.persistence.entity.ClienteJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = CuentaBancariaJpaEntity.TABLE_NAME)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaBancariaJpaEntity {
    public static final String TABLE_NAME = "CUENTAS_BANCARIAS";
    public static final String ID_COL = "id";
    public static final String TIPO_CUENTA_COL = "tipoCuenta";
    public static final String TOTAL_COL = "total";
    public static final String DNI_CLIENTE_COL = "dniCliente";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COL, nullable = false)
    private Long id;

    @Column(name = TIPO_CUENTA_COL, nullable = false)
    private String tipoCuenta;

    @Column(name = TOTAL_COL, nullable = false)
    private Double total;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = DNI_CLIENTE_COL, referencedColumnName = ClienteJpaEntity.DNI_COL, nullable = false)
    private ClienteJpaEntity cliente;
}
