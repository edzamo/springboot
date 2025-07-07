package com.pichincha.mapstruct.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Estado de cuenta.
 * @author ezamora
 *
 */
@Data
@NoArgsConstructor
public class EstadoCuentaDto {

	public String fecha;

	public String nombre;

	public Integer numeroCuenta;

	public String tipoCuenta;

	public Double saldoInicial;

	public Boolean estado;

	public Double valorMovimiento;

	public Double saldoDisponible;

}
