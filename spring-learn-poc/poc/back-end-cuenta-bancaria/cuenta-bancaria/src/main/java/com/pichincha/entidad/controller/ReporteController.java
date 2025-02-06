/**
 * 
 */
package com.pichincha.entidad.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.mapstruct.dto.EstadoCuentaDto;
import com.pichincha.service.IMovimientoService;
import com.pichincha.util.FechaUtil;

/**
 * Controller para generar reportes.
 * 
 * @author ezamora
 *
 */
@RestController
@RequestMapping("api/v1/reportes")
public class ReporteController {

	@Autowired
	private IMovimientoService movimientoService;

	@GetMapping
	public ResponseEntity<List<EstadoCuentaDto>> reporteEstadoCuenta(@PathParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate fechaInicio,
			@PathParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin, @PathParam("idCliente") Long idCliente) {
		LocalDateTime fechaIni = FechaUtil.convertirLocalDateToLocalDateTime(fechaInicio, LocalTime.MIN);
		LocalDateTime fechaFinal = FechaUtil.convertirLocalDateToLocalDateTime(fechaFin, LocalTime.MAX);
		List<EstadoCuentaDto> lista = movimientoService.obtenerEstadoCuenta(fechaIni, fechaFinal, idCliente);
		if (ObjectUtils.isEmpty(lista)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EstadoCuentaDto>>(lista, HttpStatus.OK);

	}

}
