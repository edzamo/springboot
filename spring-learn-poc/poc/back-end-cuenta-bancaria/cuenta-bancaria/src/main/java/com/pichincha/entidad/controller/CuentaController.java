package com.pichincha.entidad.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.exception.ModelNotFoundException;
import com.pichincha.mapper.MapStructMapper;
import com.pichincha.mapstruct.dto.CuentaDto;
import com.pichincha.mapstruct.dto.CuentaPutDto;
import com.pichincha.model.Cliente;
import com.pichincha.model.Cuenta;
import com.pichincha.service.IClienteService;
import com.pichincha.service.ICuentaService;

/**
 * Controller para cuenta.
 * 
 * @author ezamora
 *
 */
@RestController
@RequestMapping("api/v1/cuentas")
public class CuentaController {

	@Autowired
	private ICuentaService cuentaService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private MapStructMapper mapstructMapper;

	@PostMapping
	public ResponseEntity<Cuenta> guardarCuenta(@Valid @RequestBody CuentaDto cuentaDto) {
		Cliente cliente = clienteService.buscarPorId(cuentaDto.getIdCliente());
		if (!cliente.getEstado()) {
			throw new ModelNotFoundException(
					String.format("Cliente con id %d esta inactivo", cuentaDto.getIdCliente()));
		}
		Cuenta cuenta = mapstructMapper.convertirCuentaDtoToCuenta(cuentaDto);
		cuentaService.guardar(cuenta);
		return new ResponseEntity<Cuenta>(cuenta, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Cuenta> actualizarCuenta(@Valid @RequestBody CuentaPutDto cuentaPutDto) {
		if (Objects.nonNull(cuentaPutDto.getId())) {
			cuentaService.buscarCuentaPorCliente(cuentaPutDto.getIdCliente());
			Cuenta cuenta = cuentaService.buscarPorId(cuentaPutDto.getId());
			cuenta = mapstructMapper.convertirCuentaPutDtoToCuenta(cuentaPutDto, cuenta);
			cuentaService.actualizar(cuenta);
			return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
		} else {
			return new ResponseEntity<Cuenta>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping
	public ResponseEntity<List<Cuenta>> listarTodosCuentas() {
		List<Cuenta> deparments = cuentaService.listar();
		return new ResponseEntity<List<Cuenta>>(deparments, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
		cuentaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
