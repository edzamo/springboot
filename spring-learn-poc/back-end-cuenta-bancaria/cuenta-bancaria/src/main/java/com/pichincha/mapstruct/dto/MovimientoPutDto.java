/**
 * 
 */
package com.pichincha.mapstruct.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Dto para actualizacio de movimiento.
 * 
 * @author ezamora
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MovimientoPutDto extends MovimientoDto {

	private Long id;
	private BigDecimal saldo;
	private LocalDateTime fecha;

}
