package com.dgha.service;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T, ID> {

	T registrar(T t);

	T modificar(T t);

	Optional<T> leerPorId(ID id);

	List<T> listar();

	void eliminar(ID id);
}
