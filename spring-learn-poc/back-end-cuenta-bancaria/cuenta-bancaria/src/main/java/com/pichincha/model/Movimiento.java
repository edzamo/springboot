package com.pichincha.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * Entidad de la tabla movimiento.
 * @author ezamora
 *
 */
@Entity
@Table(name = "movimiento")
@Data
public class Movimiento implements Serializable {

	private static final long serialVersionUID = -1914005967923920381L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha")
	private LocalDateTime fecha;
	
	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "saldo")
	private BigDecimal saldo;

	@Column(name = "id_cuenta")
	private Long idCuenta;

}