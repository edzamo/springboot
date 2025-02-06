
package com.pichincha.mapstruct.dto;

import lombok.Data;

/**
 * Dto para cliente.
 * 
 * @author ezamora.
 *
 */
@Data
public class ClienteDto {

	private String nombre;
	private String genero;
	private String identificacion;
	private Integer edad;
	private String telefono;
	private String direccion;
	private String contrasena;
	private Boolean estado;

}
