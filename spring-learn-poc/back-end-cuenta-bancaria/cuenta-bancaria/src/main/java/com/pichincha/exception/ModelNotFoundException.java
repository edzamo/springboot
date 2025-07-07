package com.pichincha.exception;

/**
 * Excepcion para modelos.
 * 
 * @author ezamora
 *
 */
public class ModelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5394234538919515871L;

	public ModelNotFoundException(String mensaje) {
		super(mensaje);
	}
}
