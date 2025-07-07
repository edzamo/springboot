package com.pichincha.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Utilitario para convertir fechas.
 * 
 * @author ezamora
 *
 */
public class FechaUtil {

	public FechaUtil() {
		super();
	}

	public static LocalDateTime convertirLocalDateToLocalDateTime(LocalDate fecha, LocalTime localTime) {
		return fecha.atTime(localTime);
	}
}
