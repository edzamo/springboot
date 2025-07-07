package com.pichincha.mapstruct.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Dto para cuenta.
 * 
 * @author ezamora
 *
 */
@Data
public class CuentaDto {

	private Integer numeroCuenta;

	private String tipoCuenta;

	private BigDecimal saldoInicial;

	private Boolean estado;

	private Long idCliente;
}
