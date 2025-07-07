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

import com.pichincha.mapstruct.dto.ClienteDto;
import com.pichincha.mapstruct.dto.ClientePutDto;
import com.pichincha.model.Cliente;
import com.pichincha.util.JsonUtil;

/**
 * Test controller cliente.
 * 
 * @author ezamora
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest extends BaseController {

	private static final String END_POINT = "/api/v1/clientes";
	@Autowired
	private MockMvc mvc;

	

	@Test
	public void cuandoCreoClientePost_retornaCreatedCliente() throws Exception {
		ClienteDto cliente = generarClienteDto();

		mvc.perform(post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toBytes(cliente)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.nombre", is("Juan Osorio")));
		verify(clienteService, VerificationModeFactory.times(1)).guardar(Mockito.any());
	}

	@Test
	public void cuandoActualizoCliente_retornoActualizacionCliente() throws Exception {
		Cliente cliente = generarCliente();
		Mockito.when(clienteService.buscarPorId(cliente.getId())).thenReturn(cliente);
		ClientePutDto clienteDto = generarClientePutDto();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(END_POINT, clienteDto)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(JsonUtil.toBytes(cliente));

		mvc.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(100)))
		.andExpect(MockMvcResultMatchers.content().string(JsonUtil.toString(cliente)));
	}

	@Test
	public void cuandoBuscoCliente_retornaJsonArray() throws Exception {
		Cliente clienteUno = generarCliente();
		Cliente clienteDos = generarCliente();

		List<Cliente> clientes = Arrays.asList(clienteUno, clienteDos);

		given(clienteService.listar()).willReturn(clientes);

		mvc.perform(get(END_POINT).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].nombre", is(clienteUno.getNombre())))
				.andExpect(jsonPath("$[1].nombre", is(clienteUno.getNombre())));
		verify(clienteService, VerificationModeFactory.times(1)).listar();
	}

	
}
