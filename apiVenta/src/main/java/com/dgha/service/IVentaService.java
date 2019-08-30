package com.dgha.service;

import java.time.LocalDateTime;

import com.dgha.dto.VentasPorFechaDto;
import com.dgha.exception.EmptyObjectException;
import com.dgha.model.Venta;

public interface IVentaService extends ICRUD<Venta, Long> {

	Venta registrarVentaConDetalle(Venta venta);

	public VentasPorFechaDto obtenerVentasPorRangoDeFechas(LocalDateTime fechaInicial, LocalDateTime fechaFinal)
			throws EmptyObjectException, Exception;
}
