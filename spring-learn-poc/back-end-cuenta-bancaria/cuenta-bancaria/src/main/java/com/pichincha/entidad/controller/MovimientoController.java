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

import com.pichincha.mapper.MapStructMapper;
import com.pichincha.mapstruct.dto.MovimientoDto;
import com.pichincha.mapstruct.dto.MovimientoPutDto;
import com.pichincha.model.Movimiento;
import com.pichincha.service.IMovimientoService;

/**
 * Controller para movimiento.
 * 
 * @author ezamora
 *
 */
@RestController
@RequestMapping("api/v1/movimientos")
public class MovimientoController {

	@Autowired
	private IMovimientoService movimientoService;

	@Autowired
	private MapStructMapper mapstructMapper;

	@PostMapping
	public ResponseEntity<Movimiento> guardarMovimiento(@Valid @RequestBody MovimientoDto movimientoDto) {
		Movimiento movimiento = movimientoService.registrarMovimiento(movimientoDto);
		return new ResponseEntity<Movimiento>(movimiento, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Movimiento> actualizarMovimiento(@Valid @RequestBody MovimientoPutDto movimientoPutDto) {
		if (Objects.nonNull(movimientoPutDto.getId())) {
			movimientoService.obtenerMovimientoPorIdCuenta(movimientoPutDto.getIdCuenta());
			Movimiento movimiento = movimientoService.buscarPorId(movimientoPutDto.getId());
			movimiento = mapstructMapper.convertirMovimientoPutDtoToMovimiento(movimientoPutDto, movimiento);
			movimientoService.actualizar(movimiento);
			return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK);
		} else {
			return new ResponseEntity<Movimiento>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping
	public ResponseEntity<List<Movimiento>> listarTodosMovimientos() {
		List<Movimiento> deparments = movimientoService.listar();
		return new ResponseEntity<List<Movimiento>>(deparments, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
		movimientoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
