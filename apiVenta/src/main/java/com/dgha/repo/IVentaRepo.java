package com.dgha.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dgha.model.Venta;

public interface IVentaRepo extends JpaRepository<Venta, Long> {

	@Query("FROM Venta v WHERE v.fecha BETWEEN :fechaInicial AND :fechaFinal ORDER BY v.fecha DESC")
	List<Venta> obtenerVentasPorRangoFechas(@Param("fechaInicial") LocalDateTime fechaInicial,
			@Param("fechaFinal") LocalDateTime fechaFinal);
}
