package com.pichincha.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.entidad.repository.IMovimientoRepository;
import com.pichincha.enumacion.TipoMovimientoEnum;
import com.pichincha.exception.ModelNotFoundException;
import com.pichincha.exception.MovimientoException;
import com.pichincha.mapper.MapStructMapper;
import com.pichincha.mapstruct.dto.EstadoCuentaDto;
import com.pichincha.mapstruct.dto.MovimientoDto;
import com.pichincha.model.Cliente;
import com.pichincha.model.Cuenta;
import com.pichincha.model.Movimiento;
import com.pichincha.service.IClienteService;
import com.pichincha.service.ICuentaService;
import com.pichincha.service.IMovimientoService;
import com.pichincha.util.FechaUtil;

/**
 * Servicio para movimiento.
 * 
 * @author ezamora
 *
 */
@Service
public class MovimientoServiceImpl implements IMovimientoService {

	private static final Logger log = LoggerFactory.getLogger(MovimientoServiceImpl.class);

	private static final Double LIMITE_RETIRO = 1000D;
	@Autowired
	private IMovimientoRepository movimientoRepository;

	@Autowired
	private ICuentaService cuentaService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private MapStructMapper mapstructMapper;

	@Override
	public Movimiento guardar(Movimiento entity) {
		return movimientoRepository.save(entity);
	}

	@Override
	public Movimiento actualizar(Movimiento entity) {
		if (TipoMovimientoEnum.CREDITO.getCodigo().equalsIgnoreCase(entity.getTipoMovimiento())
				&& entity.getValor().compareTo(BigDecimal.ZERO) <= 0) {
			throw new MovimientoException(String.format("Valor ingresado es incorrecto para el tipo de movimiento %s ",
					entity.getTipoMovimiento()));
		}
		if (TipoMovimientoEnum.DEBITO.getCodigo().equalsIgnoreCase(entity.getTipoMovimiento())
				&& entity.getValor().compareTo(BigDecimal.ZERO) >= 0) {
			throw new MovimientoException(String.format("Valor ingresado es incorrecto para el tipo de movimiento %s ",
					entity.getTipoMovimiento()));
		}
		if (entity.getSaldo().compareTo(BigDecimal.ZERO) <= 0) {
			throw new MovimientoException(String.format("Saldo ingresado es incorrecto "));
		}
		return movimientoRepository.save(entity);
	}

	@Override
	public List<Movimiento> listar() {
		return movimientoRepository.findAll();
	}

	@Override
	public Movimiento buscarPorId(Long id) {
		try {
			return this.movimientoRepository.findById(id)
					.orElseThrow(() -> new Exception(String.format("Movimiento con id %d no existe", id)));
		} catch (Exception e) {
			log.error("Ha ocurrido un error comuníquese con el administrador", e);
			throw new ModelNotFoundException(e.getMessage());
		}
	}

	@Override
	public void eliminar(Long id) {
		buscarPorId(id);
		movimientoRepository.deleteById(id);
	}

	@Transactional
	public Movimiento registrarMovimiento(MovimientoDto movimientoDto) {
		Cuenta cuenta = cuentaService.buscarPorId(movimientoDto.getIdCuenta());
		Movimiento movimiento = new Movimiento();
		movimiento.setIdCuenta(cuenta.getId());

		BigDecimal saldoActual = BigDecimal.ZERO;
		Movimiento ultimoMovimiento = obtenerUltimoMovimiento();
		BigDecimal ultimoSaldo = Objects.nonNull(ultimoMovimiento) ? ultimoMovimiento.getSaldo()
				: cuenta.getSaldoInicial();

		if (TipoMovimientoEnum.CREDITO.getCodigo().equalsIgnoreCase(movimientoDto.getTipoMovimiento())) {
			saldoActual = validarCreditoMovimiento(movimientoDto, ultimoSaldo);
		}

		if (TipoMovimientoEnum.DEBITO.getCodigo().equalsIgnoreCase(movimientoDto.getTipoMovimiento())) {
			saldoActual = validarDebitoMovimiento(movimientoDto, ultimoSaldo);
		}

		movimiento.setSaldo(saldoActual);
		movimiento.setTipoMovimiento(movimientoDto.getTipoMovimiento().toUpperCase());
		movimiento.setFecha(LocalDateTime.now());
		movimiento.setValor(movimientoDto.getValor());
		guardar(movimiento);
		return movimiento;
	}

	@Override
	public Double obtenerTotalRetiroDiario(Long idCuenta, String tipoMovimiento, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		return Math.abs(movimientoRepository.obtenerTotalRetiroDiario(idCuenta, tipoMovimiento, fechaInicio, fechaFin));
	}

	private BigDecimal validarDebitoMovimiento(MovimientoDto movimientoDto, BigDecimal ultimoSaldo) {

		if (movimientoDto.getValor().compareTo(BigDecimal.ZERO) >= 0) {
			throw new MovimientoException(String.format("Valor ingresado es incorrecto para el tipo de movimiento %s ",
					movimientoDto.getTipoMovimiento()));
		}

		if (ultimoSaldo.compareTo(BigDecimal.ZERO) == 0
				|| ultimoSaldo.compareTo(BigDecimal.valueOf(Math.abs(movimientoDto.getValor().doubleValue()))) < 0) {
			throw new MovimientoException("Saldo no disponible");
		}

		LocalDate fechaActual = LocalDate.now();
		Double totalRetiroDiario = obtenerTotalRetiroDiario(movimientoDto.getIdCuenta(),
				TipoMovimientoEnum.DEBITO.getCodigo(),
				FechaUtil.convertirLocalDateToLocalDateTime(fechaActual, LocalTime.MIN),
				FechaUtil.convertirLocalDateToLocalDateTime(fechaActual, LocalTime.MAX));
		if (LIMITE_RETIRO <= totalRetiroDiario.doubleValue() + Math.abs(movimientoDto.getValor().doubleValue())) {
			throw new MovimientoException("Cupo diario Excedido");
		}
		return ultimoSaldo.add(movimientoDto.getValor());
	}

	private BigDecimal validarCreditoMovimiento(MovimientoDto movimientoDto, BigDecimal ultimoSaldo) {
		if (movimientoDto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
			throw new MovimientoException(String.format("Valor ingresado es incorrecto para el tipo de movimiento %s ",
					movimientoDto.getTipoMovimiento()));
		}
		return ultimoSaldo.add(movimientoDto.getValor());
	}

	@Override
	public Movimiento obtenerUltimoMovimiento() {
		return movimientoRepository.findFirstByOrderByFechaDesc();
	}

	@Override
	public List<EstadoCuentaDto> obtenerEstadoCuenta(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			Long idCliente) {
		validarDatosConsultaEstadoCuenta(fechaInicio, fechaFin, idCliente);
		Cliente cliente = clienteService.buscarPorId(idCliente);
		return movimientoRepository.obtenerEstadoCuenta(fechaInicio, fechaFin, cliente.getId()).stream().map(x -> {
			EstadoCuentaDto estadoCuentaDto = mapstructMapper.convertirObjetoToEstadoCuentaDto(x);
			return estadoCuentaDto;
		}).collect(Collectors.toList());

	}

	private void validarDatosConsultaEstadoCuenta(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long idCliente) {
		if (Objects.isNull(idCliente)) {
			throw new MovimientoException("Id de cliente invalido");
		}
		if (fechaInicio.isAfter(fechaFin)) {
			throw new MovimientoException("Fecha de inicio es posterior a la fecha de fin.");
		}
	}

	@Override
	public void obtenerMovimientoPorIdCuenta(Long idCuenta) {
		List<Movimiento> movimientos = movimientoRepository.findByIdCuentaOrderByFechaDesc(idCuenta);
		if (Objects.isNull(movimientos) || movimientos.isEmpty()) {
			throw new MovimientoException(String.format("No existe el movimiento para la cuenta %s ", idCuenta));
		}
	}

}
