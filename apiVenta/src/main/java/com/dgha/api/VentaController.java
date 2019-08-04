package com.dgha.api;

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

	@GetMapping("buscar/{id}")
	public ResponseEntity<Venta> leerPorId(@PathVariable("id") Long id) {
		Optional<Venta> obj = service.leerPorId(id);
		if (!obj.isPresent())
			throw new ModelNotFoundException("ID no encontrado: " + id);
		return new ResponseEntity<Venta>(obj.get(), HttpStatus.OK);
	}
}