package com.pichincha.entidad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pichincha.model.Cuenta;

/**
 * Interfaz repositorio para cuenta.
 * 
 * @author ezamora
 *
 */
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {

	List<Cuenta> findByIdClienteOrderByIdDesc(Long idCliente);

}
