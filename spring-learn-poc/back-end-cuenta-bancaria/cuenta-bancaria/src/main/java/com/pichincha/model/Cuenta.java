package com.pichincha.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entidad de la tabla cuenta.
 * 
 * @author ezamora.
 *
 */
@Entity
@Table(name = "cuenta")
@Data
public class Cuenta implements Serializable {

	private static final long serialVersionUID = -2989336796888326046L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "numero_cuenta")
	private Integer numeroCuenta;

	@Column(name = "tipo_cuenta")
	private String tipoCuenta;

	@Column(name = "saldo_inicial")
	private BigDecimal saldoInicial;
	
	@Column(name = "estado")
	private Boolean estado;

	@Column(name = "id_cliente")
	private Long idCliente;

}