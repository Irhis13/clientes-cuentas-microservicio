package com.rv.banco.modules.cuentaBancaria.infrastructure.rest.controller;

import com.rv.banco.modules.cuentaBancaria.application.usecases.CuentaBancariaUseCase;
import com.rv.banco.modules.cuentaBancaria.infrastructure.rest.mapper.CuentaBancariaRestMapper;
import com.rv.banco.modules.cuentaBancaria.infrastructure.rest.request.CreateCuentaBancariaRequest;
import com.rv.banco.modules.cuentaBancaria.infrastructure.rest.request.UpdateCuentaBancariaRequest;
import com.rv.banco.modules.cuentaBancaria.infrastructure.rest.response.CuentaBancariaRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = CuentaBancariaRestController.SWAGGER_TAG, description = CuentaBancariaRestController.DESCRIPTION_TAG)
@RequestMapping(path = CuentaBancariaRestController.CONTROLLER_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class CuentaBancariaRestController {

    // Swagger
    public static final String SWAGGER_TAG = "cuentas";
    public static final String DESCRIPTION_TAG = "Operaciones sobre cuentas bancarias";

    // Paths y summaries
    public static final String CONTROLLER_PATH = "/cuentas";
    public static final String CREATE_PATH = "";
    public static final String UPDATE_PATH = "/{idCuenta}";

    public static final String CREATE_SUMMARY = "Dar de alta una nueva cuenta bancaria para un cliente";
    public static final String UPDATE_SUMMARY = "Actualizar el total de una cuenta bancaria";

    public static final String CUENTA_CREADA = "Cuenta creada";
    public static final String CUENTA_ACTUALIZADA = "Cuenta actualizada";
    public static final String BAD_REQUEST = "Petición inválida";
    public static final String CUENTA_NO_ENCONTRADA = "Cuenta no encontrada";

    private final CuentaBancariaUseCase cuentaBancariaUseCase;

    @PostMapping(path = CREATE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = CREATE_SUMMARY)
    @ApiResponse(responseCode = "201", description = CUENTA_CREADA)
    @ApiResponse(responseCode = "400", description = BAD_REQUEST)
    public ResponseEntity<CuentaBancariaRestResponse> create(@Valid @RequestBody CreateCuentaBancariaRequest req) {
        var created = cuentaBancariaUseCase.create(req.dniCliente(), req.tipoCuenta(), req.total());
        var body = CuentaBancariaRestMapper.toResponse(created);

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.id())
                .toUri();

        return ResponseEntity.created(location).body(body);
    }

    @PutMapping(path = UPDATE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = UPDATE_SUMMARY)
    @ApiResponse(responseCode = "204", description = CUENTA_ACTUALIZADA)
    @ApiResponse(responseCode = "400", description = BAD_REQUEST)
    @ApiResponse(responseCode = "404", description = CUENTA_NO_ENCONTRADA)
    public ResponseEntity<Void> updateTotal(@PathVariable Long idCuenta, @Valid @RequestBody UpdateCuentaBancariaRequest req) {
        cuentaBancariaUseCase.updateTotal(idCuenta, req.total());
        return ResponseEntity.noContent().build();
    }
}