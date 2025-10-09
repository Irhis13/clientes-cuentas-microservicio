package com.rv.banco.modules.cliente.infrastructure.persistence.entity;

import com.rv.banco.modules.cuentaBancaria.infrastructure.persistence.entity.CuentaBancariaJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = ClienteJpaEntity.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteJpaEntity {
    public static final String TABLE_NAME = "CLIENTES";
    public static final String DNI_COL = "dni";
    public static final String NOMBRE_COL = "nombre";
    public static final String APELLIDO1_COL = "apellido1";
    public static final String APELLIDO2_COL = "apellido2";
    public static final String FECHA_NACIMIENTO_COL = "fechaNacimiento";

    @Id
    @Column(name = DNI_COL, nullable = false, length = 12)
    private String dni;

    @Column(name = NOMBRE_COL)
    private String nombre;

    @Column(name = APELLIDO1_COL)
    private String apellido1;

    @Column(name = APELLIDO2_COL)
    private String apellido2;

    @Column(name = FECHA_NACIMIENTO_COL)
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuentaBancariaJpaEntity> cuentas = new ArrayList<>();
}
