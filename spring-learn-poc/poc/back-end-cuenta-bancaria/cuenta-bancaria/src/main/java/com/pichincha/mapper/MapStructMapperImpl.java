package com.pichincha.mapper;

import java.util.Objects;

import org.springframework.stereotype.Component;

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
 * Implementacion de maper.
 * 
 * @author ezamora.
 *
 */
@Component
public class MapStructMapperImpl implements MapStructMapper {

	@Override
	public Cliente convertirClienteDtoToCliente(ClienteDto clienteDto) {
		if (clienteDto == null) {
			return null;
		}

		Cliente cliente = new Cliente();
		cliente.setNombre(clienteDto.getNombre());
		cliente.setGenero(clienteDto.getGenero());
		cliente.setIdentificacion(clienteDto.getIdentificacion());
		cliente.setEdad(clienteDto.getEdad());
		cliente.setTelefono(clienteDto.getTelefono());
		cliente.setDireccion(clienteDto.getDireccion());
		cliente.setContrasena(clienteDto.getContrasena());
		cliente.setEstado(clienteDto.getEstado());
		return cliente;
	}

	@Override
	public Cliente convertirClientePutDtoToCliente(ClientePutDto clientePutDto, Cliente cliente) {
		if (clientePutDto == null) {
			return null;
		}

		cliente.setId(Objects.nonNull(clientePutDto.getId()) ? clientePutDto.getId() : cliente.getId());
		cliente.setNombre(Objects.nonNull(clientePutDto.getNombre()) ? clientePutDto.getNombre() : cliente.getNombre());
		cliente.setGenero(Objects.nonNull(clientePutDto.getGenero()) ? clientePutDto.getGenero() : cliente.getGenero());
		cliente.setIdentificacion(Objects.nonNull(clientePutDto.getIdentificacion()) ? clientePutDto.getIdentificacion()
				: cliente.getIdentificacion());
		cliente.setEdad(Objects.nonNull(clientePutDto.getEdad()) ? clientePutDto.getEdad() : cliente.getEdad());
		cliente.setTelefono(
				Objects.nonNull(clientePutDto.getTelefono()) ? clientePutDto.getTelefono() : cliente.getTelefono());
		cliente.setDireccion(
				Objects.nonNull(clientePutDto.getDireccion()) ? clientePutDto.getDireccion() : cliente.getDireccion());
		cliente.setContrasena(Objects.nonNull(clientePutDto.getContrasena()) ? clientePutDto.getContrasena()
				: cliente.getContrasena());
		cliente.setEstado(Objects.nonNull(clientePutDto.getEstado()) ? clientePutDto.getEstado() : cliente.getEstado());
		return cliente;
	}

	@Override
	public Cuenta convertirCuentaDtoToCuenta(CuentaDto cuentaDto) {
		if (cuentaDto == null) {
			return null;
		}

		Cuenta cuenta = new Cuenta();
		cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
		cuenta.setTipoCuenta(cuentaDto.getTipoCuenta());
		cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
		cuenta.setEstado(cuentaDto.getEstado());
		cuenta.setIdCliente(cuentaDto.getIdCliente());
		return cuenta;
	}

	@Override
	public Cuenta convertirCuentaPutDtoToCuenta(CuentaPutDto cuentaPutDto, Cuenta cuenta) {
		if (cuentaPutDto == null) {
			return null;
		}
		cuenta.setId(Objects.nonNull(cuentaPutDto.getId()) ? cuentaPutDto.getId() : cuenta.getId());
		cuenta.setNumeroCuenta(Objects.nonNull(cuentaPutDto.getNumeroCuenta()) ? cuentaPutDto.getNumeroCuenta()
				: cuenta.getNumeroCuenta());
		cuenta.setTipoCuenta(
				Objects.nonNull(cuentaPutDto.getTipoCuenta()) ? cuentaPutDto.getTipoCuenta() : cuenta.getTipoCuenta());
		cuenta.setSaldoInicial(Objects.nonNull(cuentaPutDto.getSaldoInicial()) ? cuentaPutDto.getSaldoInicial()
				: cuenta.getSaldoInicial());
		cuenta.setEstado(Objects.nonNull(cuentaPutDto.getEstado()) ? cuentaPutDto.getEstado() : cuenta.getEstado());
		cuenta.setIdCliente(
				Objects.nonNull(cuentaPutDto.getIdCliente()) ? cuentaPutDto.getIdCliente() : cuenta.getIdCliente());

		return cuenta;
	}

	@Override
	public Movimiento convertirMovimientoDtoToMovimiento(MovimientoDto movimientoDto) {
		if (movimientoDto == null) {
			return null;
		}

		Movimiento movimiento = new Movimiento();
		movimiento.setIdCuenta(movimiento.getIdCuenta());
		movimiento.setTipoMovimiento(movimientoDto.getTipoMovimiento());
		movimiento.setValor(movimientoDto.getValor());

		return movimiento;
	}

	@Override
	public Movimiento convertirMovimientoPutDtoToMovimiento(MovimientoPutDto movimientoPutDto, Movimiento movimiento) {
		if (movimientoPutDto == null) {
			return null;
		}
		movimiento.setId(Objects.nonNull(movimientoPutDto.getId()) ? movimientoPutDto.getId() : movimiento.getId());
		movimiento.setFecha(Objects.nonNull(movimientoPutDto.getFecha())
				? movimientoPutDto.getFecha()
				: movimiento.getFecha());
		movimiento.setIdCuenta(Objects.nonNull(movimientoPutDto.getIdCuenta()) ? movimientoPutDto.getIdCuenta()
				: movimiento.getIdCuenta());
		movimiento.setSaldo(
				Objects.nonNull(movimientoPutDto.getSaldo()) ? movimientoPutDto.getSaldo() : movimiento.getSaldo());
		movimiento.setTipoMovimiento(
				Objects.nonNull(movimientoPutDto.getTipoMovimiento()) ? movimientoPutDto.getTipoMovimiento()
						: movimiento.getTipoMovimiento());
		movimiento.setValor(
				Objects.nonNull(movimientoPutDto.getValor()) ? movimientoPutDto.getValor() : movimiento.getValor());

		return movimiento;
	}

	@Override
	public EstadoCuentaDto convertirObjetoToEstadoCuentaDto(Object[] objeto) {
		if (objeto == null) {
			return null;
		}
		EstadoCuentaDto estadoCuentaDto = new EstadoCuentaDto();
		estadoCuentaDto.setFecha(String.valueOf(objeto[0]));
		estadoCuentaDto.setNombre(String.valueOf(objeto[1]));
		estadoCuentaDto.setNumeroCuenta(Integer.valueOf(String.valueOf(objeto[2])));
		estadoCuentaDto.setTipoCuenta(String.valueOf(objeto[3]));
		estadoCuentaDto.setSaldoInicial(Double.valueOf(String.valueOf(objeto[4])));
		estadoCuentaDto.setEstado(Boolean.valueOf(String.valueOf(objeto[5])));
		estadoCuentaDto.setValorMovimiento(Double.valueOf(String.valueOf(objeto[6])));
		estadoCuentaDto.setSaldoDisponible(Double.valueOf(String.valueOf(objeto[7])));
		return estadoCuentaDto;
	}

}
