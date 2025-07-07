package com.pichincha.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidad de la tabla cliente.
 * 
 * @author ezamora
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cliente")
@Entity
public class Cliente extends Persona implements Serializable {

	private static final long serialVersionUID = -7180277358313810242L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "contrasena")
	private String contrasena;

	@Column(name = "estado")
	private Boolean estado;

}