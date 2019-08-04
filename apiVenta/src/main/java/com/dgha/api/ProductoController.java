package com.dgha.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgha.exception.ModelNotFoundException;
import com.dgha.model.Producto;
import com.dgha.service.IProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	@GetMapping("listar")
	public ResponseEntity<List<Producto>> listar() {
		List<Producto> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("buscar/{id}")
	public ResponseEntity<Producto> leerPorId(@PathVariable("id") Long id) {
		Optional<Producto> obj = service.leerPorId(id);
		if(!obj.isPresent()) throw new ModelNotFoundException("ID no encontrado: "+id);
		return new ResponseEntity<Producto>(obj.get(), HttpStatus.OK);
	}
	
	@PostMapping("registrar")
	public ResponseEntity<Producto> registrar(@Valid @RequestBody Producto request) {
		Producto obj = service.registrar(request);
		return new ResponseEntity<Producto>(obj, HttpStatus.CREATED);
	}
	
	@PutMapping("modificar")
	public ResponseEntity<Object> modificar(@Valid @RequestBody Producto request) {
		service.modificar(request);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping("eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Long id) {
		Optional<Producto> obj = service.leerPorId(id);
		if (!obj.isPresent())
			throw new ModelNotFoundException("ID no encontrado: " + id);
		else
			service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
