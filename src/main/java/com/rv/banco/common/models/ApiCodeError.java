package com.rv.banco.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiCodeError(
        int status,
        String mensaje,
        List<ApiParamErrorField> detalles
) {}
