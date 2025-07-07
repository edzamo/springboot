package com.pichincha.mapstruct.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * Dto para actualizacion de cuenta.
 * @author ezamora
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CuentaPutDto extends CuentaDto  {
	
	private Long id;

}
