package com.pichincha.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Clase para representar persona.
 * 
 * @author ezamora
 *
 */
@Data
@MappedSuperclass
public abstract class Persona implements Serializable {

	private static final long serialVersionUID = -2829666396747458567L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "genero")
	private String genero;

	@Column(name = "edad")
	private Integer edad;

	@Column(name = "identificacion")
	private String identificacion;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "telefono")
	private String telefono;

}