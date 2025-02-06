package com.pichincha.service;

import java.time.LocalDateTime;
import java.util.List;

import com.pichincha.mapstruct.dto.EstadoCuentaDto;
import com.pichincha.mapstruct.dto.MovimientoDto;
import com.pichincha.model.Movimiento;

/**
 * Interface para movimiento .
 * 
 * @author ezamora
 *
 */
public interface IMovimientoService extends ICRUD<Movimiento> {

	Double obtenerTotalRetiroDiario(Long idCuenta, String tipoMovimiento, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	Movimiento registrarMovimiento(MovimientoDto movimientoDto);
	
	Movimiento obtenerUltimoMovimiento();
	
	void obtenerMovimientoPorIdCuenta(Long idCuenta);
	
	List<EstadoCuentaDto> obtenerEstadoCuenta(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long idCliente);
	
}
