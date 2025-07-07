package com.pichincha.service;

import java.util.List;

/**
 * CRUD.
 * 
 * @author ezamora
 *
 * @param <T>
 */
public interface ICRUD<T> {

	T guardar(T entity);

	T actualizar(T entity);

	List<T> listar();

	void eliminar(Long id);
	
	T buscarPorId(Long id);

}
