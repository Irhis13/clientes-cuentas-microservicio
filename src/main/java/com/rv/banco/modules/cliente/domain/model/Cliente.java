package com.rv.banco.modules.cliente.domain.model;

import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Cliente {

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;
    private List<CuentaBancaria> cuentas;

    public Cliente(
            String dni,
            String nombre,
            String apellido1,
            String apellido2
    ) {
        setDni(dni);
        setNombre(nombre);
        setApellido1(apellido1);
        setApellido2(apellido2);
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
}
