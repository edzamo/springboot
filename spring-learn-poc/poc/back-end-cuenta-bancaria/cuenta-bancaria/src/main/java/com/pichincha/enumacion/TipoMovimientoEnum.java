package com.pichincha.enumacion;

import lombok.Getter;
import lombok.Setter;

/**
 * Enumaracion tipo de movimineto.
 * @author ezamora
 *
 */
public enum TipoMovimientoEnum {

	CREDITO("CRE"), DEBITO("DEB");

	@Getter
	@Setter
	private String codigo;
	

	private TipoMovimientoEnum(String codigo) {
		this.codigo = codigo;
	}

	

}
