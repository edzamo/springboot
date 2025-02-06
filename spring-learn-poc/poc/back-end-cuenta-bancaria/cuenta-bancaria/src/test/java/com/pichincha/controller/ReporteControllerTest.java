package com.pichincha.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pichincha.mapstruct.dto.EstadoCuentaDto;
import com.pichincha.service.IMovimientoService;
import com.pichincha.util.FechaUtil;

/**
 * Test controller cliente.
 * 
 * @author ezamora
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ReporteControllerTest extends BaseController {

	private static final String END_POINT = "/api/v1/reportes";
	@Autowired
	private MockMvc mvc;

	@MockBean
	private IMovimientoService movimientoService;

	@Test
	public void cuandoBuscoMovimiento_retornaJsonArray() throws Exception {
		EstadoCuentaDto estadoCuenta = generarEstadoCuentaDto();

		List<EstadoCuentaDto> estadoCuentas = Arrays.asList(estadoCuenta);
		LocalDate fechaActual = LocalDate.now();
		LocalDateTime fechaIni = FechaUtil.convertirLocalDateToLocalDateTime(fechaActual, LocalTime.MIN);
		LocalDateTime fechaFinal = FechaUtil.convertirLocalDateToLocalDateTime(fechaActual, LocalTime.MAX);
		given(movimientoService.obtenerEstadoCuenta(fechaIni, fechaFinal, 1L)).willReturn(estadoCuentas);
		System.out.println(fechaIni);
		mvc.perform(get(END_POINT + "?fechaInicio=" + fechaActual + "&fechaFin=" + fechaActual + "&idCliente=1")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].tipoCuenta", is(estadoCuenta.getTipoCuenta())));
		verify(movimientoService, VerificationModeFactory.times(1)).obtenerEstadoCuenta(fechaIni, fechaFinal, 1L);
	}

}
