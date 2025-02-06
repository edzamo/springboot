package com.pichincha.mapstruct.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Dto para actualizacion de cliente.
 * 
 * @author ezamora
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientePutDto extends ClienteDto {
	private Long id;
}
