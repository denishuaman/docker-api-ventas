package com.dgha.dto;

import java.util.List;

import com.dgha.model.Venta;

public class VentasPorFechaDto {

	private Double importeTotal;

	private List<Venta> ventas;

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

}
