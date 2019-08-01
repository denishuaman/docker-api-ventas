package com.dgha.service;

import com.dgha.model.Persona;

public interface IPersonaService extends ICRUD<Persona> {

	public Persona buscarPorDni(String dni);

	public void eliminarPorDni(String dni);

}
