package com.rv.banco.modules.cuentaBancaria.domain.exception;

import com.rv.banco.common.exceptions.ApiCodeException;

public class CuentaBancariaException extends ApiCodeException {

  public static final int ERROR_CODE = 400002;

  public CuentaBancariaException(final String message) {
    super(message, ERROR_CODE);
  }
}
