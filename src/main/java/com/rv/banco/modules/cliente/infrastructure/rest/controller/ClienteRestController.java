package com.rv.banco.modules.cliente.infrastructure.rest.controller;

import com.rv.banco.modules.cliente.application.usecases.ClienteUseCases;
import com.rv.banco.modules.cliente.infrastructure.rest.mapper.ClienteRestMapper;
import com.rv.banco.modules.cliente.infrastructure.rest.response.ClienteRestResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ClienteRestController.CONTROLLER_PATH)
@Validated
public class ClienteRestController {

    public static final String CONTROLLER_PATH = "/clientes";

    public static final String GET_BY_DNI_PATH = "/{dni}";
    public static final String GET_MAYORES_EDAD_PATH = "/mayores-de-edad";
    public static final String GET_CON_CUENTA_SUPERIOR_PATH = "/con-cuenta-superior-a/{cantidad}";

    private final ClienteUseCases clienteUseCases;

    @GetMapping
    public ResponseEntity<List<ClienteRestResponse>> getAll() {
        var body = clienteUseCases.getAll().stream().map(ClienteRestMapper::toResponse).toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = GET_MAYORES_EDAD_PATH)
    public ResponseEntity<List<ClienteRestResponse>> getMayoresDeEdad() {
        var body = clienteUseCases.getMayoresDeEdad().stream().map(ClienteRestMapper::toResponse).toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = GET_CON_CUENTA_SUPERIOR_PATH)
    public ResponseEntity<List<ClienteRestResponse>> getConSumaCuentasSuperiorA(
            @PathVariable @Min(0) double cantidad
    ) {
        var body = clienteUseCases.getConSumaCuentasSuperiorA(cantidad).stream().map(ClienteRestMapper::toResponse).toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = GET_BY_DNI_PATH)
    public ResponseEntity<ClienteRestResponse> getByDni(
            @PathVariable final String dni
    ) {
        var cliente = clienteUseCases.getByDni(dni);
        return ResponseEntity.ok(ClienteRestMapper.toResponse(cliente));
    }
}
