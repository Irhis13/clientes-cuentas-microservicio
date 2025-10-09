package com.rv.banco.modules.cliente.infrastructure.rest.controller;

import com.rv.banco.modules.cliente.application.usecases.ClienteUseCases;
import com.rv.banco.modules.cliente.infrastructure.rest.mapper.ClienteRestMapper;
import com.rv.banco.modules.cliente.infrastructure.rest.response.ClienteRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = ClienteRestController.SWAGGER_TAG, description = ClienteRestController.DESCRIPTION_TAG)
@RequestMapping(path = ClienteRestController.CONTROLLER_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ClienteRestController {

    public static final String SWAGGER_TAG = "clientes";
    public static final String DESCRIPTION_TAG = "Operaciones para gestionar clientes y sus cuentas";

    public static final String CONTROLLER_PATH = "/clientes";
    public static final String GET_CLIENTES_SUMMARY = "Lista de todos los clientes con sus datos personales y cuentas bancarias";
    public static final String GET_BY_DNI_PATH = "/{dni}";
    public static final String GET_BY_DNI_SUMMARY = "Obtener cliente por DNI";
    public static final String GET_MAYORES_EDAD_PATH = "/mayores-de-edad";
    public static final String GET_MAYORES_EDAD_SUMMARY = "Listar clientes mayores de edad";
    public static final String GET_CON_CUENTA_SUPERIOR_PATH = "/con-cuenta-superior-a/{cantidad}";
    public static final String GET_CON_CUENTA_SUPERIOR_SUMMARY = "Listar clientes con cuentas superiores a una cantidad";
    public static final String CLIENTE_ENCONTRADO = "Cliente encontrado";
    public static final String CLIENTE_NO_ENCONTRADO = "Cliente no encontrado";

    private final ClienteUseCases clienteUseCases;

    @GetMapping
    @Operation(summary = GET_CLIENTES_SUMMARY)
    public ResponseEntity<List<ClienteRestResponse>> getAll() {
        var body = clienteUseCases.getAll().stream().map(ClienteRestMapper::toResponse).toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = GET_MAYORES_EDAD_PATH)
    @Operation(summary = GET_MAYORES_EDAD_SUMMARY)
    public ResponseEntity<List<ClienteRestResponse>> getMayoresDeEdad() {
        var body = clienteUseCases.getMayoresDeEdad().stream().map(ClienteRestMapper::toResponse).toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = GET_CON_CUENTA_SUPERIOR_PATH)
    @Operation(summary = GET_CON_CUENTA_SUPERIOR_SUMMARY )
    public ResponseEntity<List<ClienteRestResponse>> getConSumaCuentasSuperiorA(
            @PathVariable @PositiveOrZero double cantidad
    ) {
        var body = clienteUseCases.getConCuentasSuperiorA(cantidad).stream().map(ClienteRestMapper::toResponse).toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping(path = GET_BY_DNI_PATH)
    @Operation(summary = GET_BY_DNI_SUMMARY)
    @ApiResponse(responseCode = "200", description = CLIENTE_ENCONTRADO)
    @ApiResponse(responseCode = "404", description = CLIENTE_NO_ENCONTRADO)
    public ResponseEntity<ClienteRestResponse> getByDni(@PathVariable String dni) {
        var cliente = clienteUseCases.getCliente(dni);
        return ResponseEntity.ok(ClienteRestMapper.toResponse(cliente));
    }
}
