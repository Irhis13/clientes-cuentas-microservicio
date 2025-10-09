package com.rv.banco.modules.cliente.rest.controller;

import com.rv.banco.common.handlers.RestExceptionHandler;
import com.rv.banco.modules.cliente.application.usecases.ClienteUseCases;
import com.rv.banco.modules.cliente.domain.exception.ClienteNotFoundException;
import com.rv.banco.modules.cliente.domain.model.Cliente;
import com.rv.banco.modules.cliente.infrastructure.rest.controller.ClienteRestController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClienteRestController.class)
@Import(RestExceptionHandler.class)
class ClienteRestControllerTest {

    @Autowired MockMvc mvc;
    @MockitoBean ClienteUseCases useCases;

    @Test
    void getAll_devuelveLista_200() throws Exception {
        var cli = new Cliente("11111111A", "Juan", "Pérez", "López");
        cli.setFechaNacimiento(LocalDate.of(1980, 1, 1));
        Mockito.when(useCases.getAll()).thenReturn(List.of(cli));

        mvc.perform(get("/clientes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].dni").value("11111111A"))
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    void getMayoresDeEdad_200() throws Exception {
        Mockito.when(useCases.getMayoresDeEdad()).thenReturn(List.of());
        mvc.perform(get("/clientes/mayores-de-edad"))
                .andExpect(status().isOk());
    }

    @Test
    void getConCuentaSuperior_200() throws Exception {
        Mockito.when(useCases.getConCuentasSuperiorA(50000)).thenReturn(List.of());
        mvc.perform(get("/clientes/con-cuenta-superior-a/50000"))
                .andExpect(status().isOk());
    }

    @Test
    void getConCuentaSuperior_parametroNegativo_400() throws Exception {
        mvc.perform(get("/clientes/con-cuenta-superior-a/-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getConCuentaSuperior_parametroNoNumerico_400() throws Exception {
        mvc.perform(get("/clientes/con-cuenta-superior-a/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByDni_200() throws Exception {
        var cli = new Cliente("11111111A", "Juan", "Pérez", "López");
        cli.setFechaNacimiento(LocalDate.of(1980, 1, 1));
        Mockito.when(useCases.getCliente("11111111A")).thenReturn(cli);

        mvc.perform(get("/clientes/11111111A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value("11111111A"))
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void getByDni_noExiste_404() throws Exception {
        Mockito.when(useCases.getCliente("999"))
                .thenThrow(new ClienteNotFoundException("999"));

        mvc.perform(get("/clientes/999"))
                .andExpect(status().isNotFound());
    }
}