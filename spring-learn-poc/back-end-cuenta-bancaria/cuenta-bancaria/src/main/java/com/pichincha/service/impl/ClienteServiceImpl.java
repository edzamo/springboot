package com.pichincha.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.entidad.repository.IClienteRepository;
import com.pichincha.exception.ModelNotFoundException;
import com.pichincha.model.Cliente;
import com.pichincha.service.IClienteService;

/**
 * Servicio para cliente.
 * 
 * @author ezamora
 *
 */
@Service
public class ClienteServiceImpl implements IClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Autowired
	private IClienteRepository clienteRepository;


	@Override
	public Cliente guardar(Cliente entity) {
		return clienteRepository.save(entity);
	}

	@Override
	public Cliente actualizar(Cliente entity) {
		return clienteRepository.save(entity);
	}

	@Override
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		try {
			return this.clienteRepository.findById(id)
					.orElseThrow(() -> new Exception(String.format("Cliente con id %d no existe", id)));
		} catch (Exception e) {
			log.error("Ha ocurrido un error comuníquese con el administrador", e);
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@Override
	public void eliminar(Long id) {
		buscarPorId(id);
		clienteRepository.deleteById(id);
	}

}
