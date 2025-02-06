/**
 * 
 */
package com.pichincha.mapstruct.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Dto para movimieto.
 * 
 * @author ezamora.
 *
 */
@Data
public class MovimientoDto {

	private String tipoMovimiento;
	private BigDecimal valor;
	private Long idCuenta;
}
