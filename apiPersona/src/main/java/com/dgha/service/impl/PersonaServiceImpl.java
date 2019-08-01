package com.dgha.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgha.model.Persona;
import com.dgha.repo.IPersonaRepo;
import com.dgha.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaRepo repo;
	
	@Override
	public Persona registrar(Persona t) {
		return repo.save(t);
	}

	@Override
	public Persona modificar(Persona t) {
		return repo.save(t);
	}
	
	@Override
	public Persona buscarPorDni(String dni) {
		return repo.buscarPorDni(dni);
	}

	@Override
	public Optional<Persona> leerPorId(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<Persona> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminarPorDni(String dni) {
		repo.eliminarPorDni(dni);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

}
