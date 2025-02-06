package com.pichincha.entidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pichincha.model.Cliente;

/**
 * Interfaz repositorio para cliente.
 * 
 * @author ezamora
 *
 */

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
