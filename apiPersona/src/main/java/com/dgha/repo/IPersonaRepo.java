package com.dgha.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dgha.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {

	@Query("SELECT p FROM Persona p WHERE p.dni = :dni")
	Persona buscarPorDni(@Param("dni") String dni);

	@Transactional
	@Modifying
	@Query("DELETE FROM Persona p WHERE p.dni = :dni")
	void eliminarPorDni(@Param("dni") String dni);
}
