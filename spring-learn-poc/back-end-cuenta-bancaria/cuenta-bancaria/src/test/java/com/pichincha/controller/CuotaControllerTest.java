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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.pichincha.mapstruct.dto.CuentaDto;
import com.pichincha.mapstruct.dto.CuentaPutDto;
import com.pichincha.model.Cliente;
import com.pichincha.model.Cuenta;
import com.pichincha.util.JsonUtil;

/**
 * Test controller cliente.
 * 
 * @author ezamora
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CuotaControllerTest extends BaseController {

	private static final String END_POINT = "/api/v1/cuentas";
	@Autowired
	private MockMvc mvc;



	@Test
	public void cuandoCreoCuotaPost_retornaCreatedCuota() throws Exception {
		CuentaDto cuentaDto = generarCuentaDto();
		Cliente cliente = generarCliente();
		Mockito.when(clienteService.buscarPorId(cuentaDto.getIdCliente())).thenReturn(cliente);
		mvc.perform(post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toBytes(cuentaDto)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.tipoCuenta", is("Ahorros")));
		verify(cuentaService, VerificationModeFactory.times(1)).guardar(Mockito.any());
	}

	@Test
	public void cuandoActualizoCuota_retornoActualizacionCuota() throws Exception {
		Cuenta cuenta = generarCuenta();
		Mockito.when(cuentaService.buscarPorId(cuenta.getId())).thenReturn(cuenta);
		Cliente cliente = generarCliente();
		Mockito.when(clienteService.buscarPorId(cuenta.getIdCliente())).thenReturn(cliente);
		CuentaPutDto clienteDto = generarCuentaPutDto();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(END_POINT, clienteDto)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(JsonUtil.toBytes(cuenta));
		mvc.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
		.andExpect(MockMvcResultMatchers.content().string(JsonUtil.toString(cuenta)));
	}

	@Test
	public void cuandoBuscoCuota_retornaJsonArray() throws Exception {
		Cuenta cuentaUno = generarCuenta();
		Cuenta cuentaDos = generarCuenta();

		List<Cuenta> cuentas = Arrays.asList(cuentaUno, cuentaDos);

		given(cuentaService.listar()).willReturn(cuentas);

		mvc.perform(get(END_POINT).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].numeroCuenta", is(cuentaUno.getNumeroCuenta())))
				.andExpect(jsonPath("$[1].numeroCuenta", is(cuentaUno.getNumeroCuenta())));
		verify(cuentaService, VerificationModeFactory.times(1)).listar();
	}


}
