package com.pichincha.service;

import com.pichincha.model.Cuenta;

/**
 * Interface para cuenta.
 * 
 * @author ezamora
 *
 */
public interface ICuentaService extends ICRUD<Cuenta> {

	void buscarCuentaPorCliente(Long idCliente);
}
