package com.pichincha.mapper;

import org.mapstruct.Mapper;

import com.pichincha.mapstruct.dto.ClienteDto;
import com.pichincha.mapstruct.dto.ClientePutDto;
import com.pichincha.mapstruct.dto.CuentaDto;
import com.pichincha.mapstruct.dto.CuentaPutDto;
import com.pichincha.mapstruct.dto.EstadoCuentaDto;
import com.pichincha.mapstruct.dto.MovimientoDto;
import com.pichincha.mapstruct.dto.MovimientoPutDto;
import com.pichincha.model.Cliente;
import com.pichincha.model.Cuenta;
import com.pichincha.model.Movimiento;

/**
 * Interfaz para mapper de objetos.
 * 
 * @author ezamora
 *
 */
@Mapper(componentModel = "spring")
public interface MapStructMapper {

	Cliente convertirClienteDtoToCliente(ClienteDto clienteDto);

	Cliente convertirClientePutDtoToCliente(ClientePutDto clienteDto, Cliente cliente);
	
	Cuenta convertirCuentaDtoToCuenta(CuentaDto cuentaDto);

	Cuenta convertirCuentaPutDtoToCuenta(CuentaPutDto cuentaDto, Cuenta cuenta);
	
	Movimiento convertirMovimientoDtoToMovimiento(MovimientoDto movimientoDto);

	Movimiento convertirMovimientoPutDtoToMovimiento(MovimientoPutDto movimientoPutDto, Movimiento movimiento);
	
	EstadoCuentaDto convertirObjetoToEstadoCuentaDto(final Object[] objeto);
}
