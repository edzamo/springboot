package com.pichincha.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.pichincha.enumacion.TipoMovimientoEnum;
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
import com.pichincha.service.IClienteService;
import com.pichincha.service.ICuentaService;

public class BaseController {
	@MockBean
	protected IClienteService clienteService;
	
	@MockBean
	protected ICuentaService cuentaService;
	
	protected Cuenta generarCuenta() {
		Cuenta cuenta = new Cuenta();
		cuenta.setId(1L);
		cuenta.setNumeroCuenta(495878);
		cuenta.setIdCliente(1L);
		cuenta.setSaldoInicial(BigDecimal.ZERO);
		cuenta.setTipoCuenta("Ahorros");
		cuenta.setEstado(Boolean.TRUE);
		return cuenta;
	}

	protected CuentaDto generarCuentaDto() {
		CuentaDto cuenta = new CuentaDto();
		cuenta.setNumeroCuenta(495878);
		cuenta.setIdCliente(1L);
		cuenta.setSaldoInicial(BigDecimal.ZERO);
		cuenta.setTipoCuenta("Ahorros");
		cuenta.setEstado(Boolean.TRUE);
		return cuenta;
	}

	protected CuentaPutDto generarCuentaPutDto() {
		CuentaPutDto cuenta = new CuentaPutDto();
		cuenta.setId(1L);
		cuenta.setNumeroCuenta(495878);
		cuenta.setIdCliente(1L);
		cuenta.setSaldoInicial(BigDecimal.ZERO);
		cuenta.setTipoCuenta("Ahorros");
		cuenta.setEstado(Boolean.TRUE);
		return cuenta;
	}
	
	protected Cliente generarCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(100L);
		cliente.setNombre("Juan Osorio");
		cliente.setGenero("M");
		cliente.setEdad(28);
		cliente.setIdentificacion("1718529934");
		cliente.setDireccion("13 junio y Equinoccial");
		cliente.setTelefono("098874587");
		cliente.setContrasena("1245");
		cliente.setEstado(Boolean.TRUE);
		return cliente;
	}
	
	protected ClienteDto generarClienteDto() {
		ClienteDto cliente = new ClienteDto();
		cliente.setNombre("Juan Osorio");
		cliente.setGenero("M");
		cliente.setEdad(28);
		cliente.setIdentificacion("1718529934");
		cliente.setDireccion("13 junio y Equinoccial");
		cliente.setTelefono("098874587");
		cliente.setContrasena("1245");
		cliente.setEstado(Boolean.TRUE);
		return cliente;
	}

	protected ClientePutDto generarClientePutDto() {
		ClientePutDto cliente = new ClientePutDto();
		cliente.setId(100L);
		cliente.setNombre("Juan Osorio");
		cliente.setGenero("M");
		cliente.setEdad(28);
		cliente.setIdentificacion("1718529934");
		cliente.setDireccion("13 junio y Equinoccial");
		cliente.setTelefono("098874587");
		cliente.setContrasena("1245");
		cliente.setEstado(Boolean.TRUE);
		return cliente;
	}
	
	protected MovimientoDto generarMovimientoDto() {
		MovimientoDto movimiento = new MovimientoDto();
		movimiento.setIdCuenta(1L);
		movimiento.setTipoMovimiento(TipoMovimientoEnum.CREDITO.getCodigo());
		movimiento.setValor(BigDecimal.valueOf(150));
		return movimiento;
	}
	
	protected Movimiento generarMovimiento() {
		Movimiento movimiento = new Movimiento();
		movimiento.setId(1L);
		movimiento.setIdCuenta(1L);
		movimiento.setTipoMovimiento(TipoMovimientoEnum.CREDITO.getCodigo());
		movimiento.setValor(BigDecimal.valueOf(150));
		movimiento.setSaldo(BigDecimal.valueOf(150));
		movimiento.setFecha(LocalDateTime.now());
		return movimiento;
	}
	
	protected MovimientoPutDto generarMovimientoPutDto() {
		MovimientoPutDto movimiento = new MovimientoPutDto();
		movimiento.setId(1L);
		movimiento.setIdCuenta(1L);
		movimiento.setTipoMovimiento(TipoMovimientoEnum.CREDITO.getCodigo());
		movimiento.setValor(BigDecimal.valueOf(150));
		movimiento.setSaldo(BigDecimal.valueOf(150));
		movimiento.setFecha(LocalDateTime.now());
		return movimiento;
	}
	
	protected EstadoCuentaDto generarEstadoCuentaDto() {
		EstadoCuentaDto estadoCuentaDto= new EstadoCuentaDto();
		estadoCuentaDto.setFecha("25/06/2022");
		estadoCuentaDto.setEstado(true);
		estadoCuentaDto.setNombre("Marianela Montalvo");
		estadoCuentaDto.setNumeroCuenta(225487);
		estadoCuentaDto.setSaldoDisponible(700D);
		estadoCuentaDto.setSaldoInicial(100D);
		estadoCuentaDto.setTipoCuenta("Corriente");
		estadoCuentaDto.setValorMovimiento(600D);
		return estadoCuentaDto;
	}
}
