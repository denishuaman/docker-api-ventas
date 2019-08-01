package com.dgha.service;

import java.util.List;
import java.util.Optional;

import com.dgha.model.Persona;

public interface ICRUD<T> {

	T registrar(T t);

	T modificar(T t);

	Optional<Persona> leerPorId(Integer id);

	List<T> listar();

	void eliminar(Integer id);
}
