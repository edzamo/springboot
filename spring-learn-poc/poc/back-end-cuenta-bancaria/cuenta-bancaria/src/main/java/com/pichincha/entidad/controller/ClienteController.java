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
import com.pichincha.mapstruct.dto.ClienteDto;
import com.pichincha.mapstruct.dto.ClientePutDto;
import com.pichincha.model.Cliente;
import com.pichincha.service.IClienteService;

/**
 * Controller para clientes.
 * 
 * @author ezamora
 *
 */
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private MapStructMapper mapstructMapper;

	@PostMapping
	public ResponseEntity<Cliente> guardarCliente(@Valid @RequestBody ClienteDto clienteDto) {
		Cliente cliente = mapstructMapper.convertirClienteDtoToCliente(clienteDto);
		clienteService.guardar(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Cliente> actualizarCliente(@Valid @RequestBody ClientePutDto clientePutDto) {
		if (Objects.nonNull(clientePutDto.getId())) {
			Cliente cliente = clienteService.buscarPorId(clientePutDto.getId());
			cliente = mapstructMapper.convertirClientePutDtoToCliente(clientePutDto, cliente);
			clienteService.actualizar(cliente);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<Cliente>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodosClientes() {
		List<Cliente> deparments = clienteService.listar();
		return new ResponseEntity<List<Cliente>>(deparments, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
		clienteService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
