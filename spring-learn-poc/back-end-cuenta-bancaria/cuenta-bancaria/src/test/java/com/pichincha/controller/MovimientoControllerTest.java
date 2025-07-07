package com.pichincha.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pichincha.mapstruct.dto.MovimientoDto;
import com.pichincha.mapstruct.dto.MovimientoPutDto;
import com.pichincha.model.Cuenta;
import com.pichincha.model.Movimiento;
import com.pichincha.service.IMovimientoService;
import com.pichincha.util.JsonUtil;

/**
 * Test controller cliente.
 * 
 * @author ezamora
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MovimientoControllerTest extends BaseController {

	private static final String END_POINT = "/api/v1/movimientos";
	@Autowired
	private MockMvc mvc;

	@MockBean
	private IMovimientoService movimientoService;

	@Test
	public void cuandoCreoMovimientoPost_retornaCreatedMovimiento() throws Exception {
		MovimientoDto movimientoDto = generarMovimientoDto();
		Movimiento movimiento = generarMovimiento();
		Cuenta cuenta = generarCuenta();
		Mockito.when(cuentaService.buscarPorId(movimientoDto.getIdCuenta())).thenReturn(cuenta);
		Mockito.when(movimientoService.obtenerUltimoMovimiento()).thenReturn(movimiento);
		

		mvc.perform(post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toBytes(movimientoDto)))
				.andExpect(status().isCreated());
		verify(movimientoService, VerificationModeFactory.times(1)).registrarMovimiento(Mockito.any());
	}

	@Test
	public void cuandoActualizoMovimiento_retornoActualizacionMovimiento() throws Exception {
		Movimiento movimiento = generarMovimiento();
		MovimientoPutDto movimientoPutDto = generarMovimientoPutDto();
		Mockito.doNothing().when(movimientoService).obtenerMovimientoPorIdCuenta(movimientoPutDto.getIdCuenta());
		Mockito.when(movimientoService.buscarPorId(movimientoPutDto.getId())).thenReturn(movimiento);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(END_POINT, movimientoPutDto)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(JsonUtil.toBytes(movimiento));
		mvc.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	public void cuandoBuscoMovimiento_retornaJsonArray() throws Exception {
		Movimiento movimientoUno = generarMovimiento();
		Movimiento movimientoDos = generarMovimiento();

		List<Movimiento> movimientos = Arrays.asList(movimientoUno, movimientoDos);

		given(movimientoService.listar()).willReturn(movimientos);

		mvc.perform(get(END_POINT).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].tipoMovimiento", is(movimientoUno.getTipoMovimiento())))
				.andExpect(jsonPath("$[1].tipoMovimiento", is(movimientoUno.getTipoMovimiento())));
		verify(movimientoService, VerificationModeFactory.times(1)).listar();
	}

}
