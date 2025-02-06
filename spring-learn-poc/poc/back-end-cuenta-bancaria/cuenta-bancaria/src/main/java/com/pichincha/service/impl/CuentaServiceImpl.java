package com.pichincha.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.entidad.repository.ICuentaRepository;
import com.pichincha.exception.ModelNotFoundException;
import com.pichincha.model.Cuenta;
import com.pichincha.service.ICuentaService;

/**
 * Servicio para cuenta.
 * 
 * @author ezamora
 *
 */
@Service
public class CuentaServiceImpl implements ICuentaService {

	private static final Logger log = LoggerFactory.getLogger(CuentaServiceImpl.class);

	@Autowired
	private ICuentaRepository cuentaRepository;

	@Override
	public Cuenta guardar(Cuenta entity) {
		return cuentaRepository.save(entity);
	}

	@Override
	public Cuenta actualizar(Cuenta entity) {
		return cuentaRepository.save(entity);
	}

	@Override
	public List<Cuenta> listar() {
		return cuentaRepository.findAll();
	}

	@Override
	public Cuenta buscarPorId(Long id) {
		try {
			return this.cuentaRepository.findById(id)
					.orElseThrow(() -> new Exception(String.format("Cuenta con id %d no existe", id)));
		} catch (Exception e) {
			log.error("Ha ocurrido un error comuníquese con el administrador", e);
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@Override
	public void eliminar(Long id) {
		buscarPorId(id);
		cuentaRepository.deleteById(id);
	}

	@Override
	public void buscarCuentaPorCliente(Long idCliente) {
		List<Cuenta> cuentas = cuentaRepository.findByIdClienteOrderByIdDesc(idCliente);
		if (Objects.isNull(cuentas) || cuentas.isEmpty()) {
			throw new ModelNotFoundException(String.format("No existe la cuenta para el cliente con id %d", idCliente));
		}
	}

}
