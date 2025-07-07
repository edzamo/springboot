package com.pichincha.entidad.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pichincha.model.Movimiento;

/**
 * Interfaz repositorio para movimiento.
 * 
 * @author ezamora.
 *
 */
public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {

	@Query(value = "SELECT COALESCE(SUM(c.valor),0) FROM Movimiento c "
			+ "WHERE c.idCuenta= :idCuenta AND c.tipoMovimiento= :tipoMovimiento AND c.fecha BETWEEN :fechaInicio AND :fechaFin")
	Double obtenerTotalRetiroDiario(Long idCuenta, String tipoMovimiento, LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

	Movimiento findFirstByOrderByFechaDesc();

	@Query(value = "SELECT TO_CHAR(m.fecha, 'dd/MM/yyyy') as fecha, cl.nombre, cu.numero_cuenta as numeroCuenta, cu.tipo_cuenta as tipoCuenta, "
			+ "	cu.saldo_inicial as saldoInicial, cu.estado, m.valor as valorMovimiento, m.saldo as saldoDisponible "
			+ "	FROM movimiento m" + " INNER JOIN cuenta cu ON  m.id_cuenta = cu.id"
			+ " INNER JOIN cliente cl ON  cu.id_cliente = cl.id"
			+ " WHERE m.fecha between :fechaInicio and :fechaFin and cl.id = :idCliente "
			+ " ORDER BY m.fecha DESC", nativeQuery = true)
	List<Object[]> obtenerEstadoCuenta(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long idCliente);

	List<Movimiento> findByIdCuentaOrderByFechaDesc(Long idCuenta);
}
