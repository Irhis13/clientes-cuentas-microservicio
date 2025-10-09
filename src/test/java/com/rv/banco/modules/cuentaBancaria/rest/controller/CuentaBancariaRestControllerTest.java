package com.rv.banco.modules.cuentaBancaria.rest.controller;

import com.rv.banco.common.handlers.RestExceptionHandler;
import com.rv.banco.modules.cuentaBancaria.application.usecases.CuentaBancariaUseCase;
import com.rv.banco.modules.cuentaBancaria.domain.exception.CuentaBancariaNotFoundException;
import com.rv.banco.modules.cuentaBancaria.domain.model.CuentaBancaria;
import com.rv.banco.modules.cuentaBancaria.infrastructure.rest.controller.CuentaBancariaRestController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CuentaBancariaRestController.class)
@Import(RestExceptionHandler.class)
class CuentaBancariaRestControllerTest {

    @Autowired MockMvc mvc;
    @MockitoBean CuentaBancariaUseCase useCase;

    @Test
    void postCuenta_creaNueva_201() throws Exception {
        var cuenta = new CuentaBancaria(1L, "NORMAL", 50000.0);
        Mockito.when(useCase.create("11111111A", "NORMAL", 50000.0)).thenReturn(cuenta);

        mvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"dniCliente":"11111111A","tipoCuenta":"NORMAL","total":50000}
                        """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoCuenta").value("NORMAL"))
                .andExpect(jsonPath("$.total").value(50000.0));
    }

    @Test
    void postCuenta_totalNegativo_400() throws Exception {
        mvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"dniCliente":"11111111A","tipoCuenta":"NORMAL","total":-5}
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postCuenta_faltaCampoObligatorio_400() throws Exception {
        mvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"dniCliente":"11111111A","tipoCuenta":"","total":10}
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void putCuenta_actualiza_204() throws Exception {
        mvc.perform(put("/cuentas/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"total":180000}
                        """))
                .andExpect(status().isNoContent());

        Mockito.verify(useCase).updateTotal(10L, 180000.0);
    }

    @Test
    void putCuenta_noExiste_404() throws Exception {
        Mockito.doThrow(new CuentaBancariaNotFoundException(99L))
                .when(useCase).updateTotal(99L, 100.0);

        mvc.perform(put("/cuentas/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"total":100}
                        """))
                .andExpect(status().isNotFound());
    }

    @Test
    void putCuenta_totalNegativo_400() throws Exception {
        mvc.perform(put("/cuentas/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"total":-1}
                        """))
                .andExpect(status().isBadRequest());
    }
}