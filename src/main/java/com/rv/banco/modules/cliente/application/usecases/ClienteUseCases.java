package com.rv.banco.modules.cliente.application.usecases;

import com.rv.banco.modules.cliente.domain.model.Cliente;

import java.util.List;

public interface ClienteUseCases {

    List<Cliente> getAll();

    List<Cliente> getMayoresDeEdad();

    List<Cliente> getConCuentasSuperiorA(double cantidad);

    Cliente getCliente(String dni);
}
