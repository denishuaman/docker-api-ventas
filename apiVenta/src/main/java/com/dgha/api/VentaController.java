package com.dgha.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgha.dto.FiltroConsultaVentaDto;
import com.dgha.dto.VentasPorFechaDto;
import com.dgha.exception.EmptyObjectException;
import com.dgha.exception.ModelNotFoundException;
import com.dgha.model.Venta;
import com.dgha.service.IVentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
	private IVentaService service;

	@PostMapping("registrar")
	public ResponseEntity<Venta> registrar(@Valid @RequestBody Venta venta) {
		Venta obj = service.registrarVentaConDetalle(venta);
		return new ResponseEntity<Venta>(obj, HttpStatus.CREATED);
	}

	@GetMapping("listar")
	public ResponseEntity<List<Venta>> listar() {
		List<Venta> ventas = service.listar();
		return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);
	}

	@GetMapping("buscar/{id}")
	public ResponseEntity<Venta> leerPorId(@PathVariable("id") Long id) {
		Optional<Venta> obj = service.leerPorId(id);
		if (!obj.isPresent())
			throw new ModelNotFoundException("ID no encontrado: " + id);
		return new ResponseEntity<Venta>(obj.get(), HttpStatus.OK);
	}

	@PostMapping("consultar-por-fecha")
	public ResponseEntity<VentasPorFechaDto> consultarVentasPorFecha(@RequestBody FiltroConsultaVentaDto filtro)
			throws EmptyObjectException, Exception {
		VentasPorFechaDto ventasPorFecha = service.obtenerVentasPorRangoDeFechas(filtro.getFechaInicial(),
				filtro.getFechaFinal());
		return new ResponseEntity<VentasPorFechaDto>(ventasPorFecha, HttpStatus.OK);
	}
}
