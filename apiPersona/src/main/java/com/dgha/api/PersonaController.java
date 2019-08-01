package com.dgha.api;

import java.util.List;

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
import com.dgha.model.Persona;
import com.dgha.service.IPersonaService;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

	@Autowired
	private IPersonaService service;

	@GetMapping("listar")
	public ResponseEntity<List<Persona>> listar() {
		List<Persona> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("buscar/{dni}")
	public ResponseEntity<Persona> leerPorDni(@PathVariable("dni") String dni) {
		Persona obj = service.buscarPorDni(dni);
		if (obj == null)
			throw new ModelNotFoundException("DNI no encontrado: " + dni);
		return new ResponseEntity<Persona>(obj, HttpStatus.OK);
	}

	@PostMapping("registrar")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Persona request) {
		Persona obj = service.registrar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}

	@PutMapping("modificar")
	public ResponseEntity<Object> modificar(@Valid @RequestBody Persona request) {
		service.modificar(request);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping("eliminar/{dni}")
	public ResponseEntity<Object> eliminar(@PathVariable("dni") String dni) {
		Persona obj = service.buscarPorDni(dni);
		if (obj == null)
			throw new ModelNotFoundException("DNI no encontrado: " + dni);
		else
			service.eliminarPorDni(dni);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
