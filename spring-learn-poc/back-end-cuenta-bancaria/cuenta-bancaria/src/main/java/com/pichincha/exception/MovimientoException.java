package com.pichincha.exception;

/**
 * Clase para manejar excepciones de movimientos.
 * 
 * @author ezamora
 *
 */
public class MovimientoException extends RuntimeException {

	private static final long serialVersionUID = 9091251539936448619L;

	public MovimientoException(String mensaje) {
		super(mensaje);
	}
}
