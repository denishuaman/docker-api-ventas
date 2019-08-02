package com.dgha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgha.model.DetalleVenta;

public interface IDetalleVentaRepo extends JpaRepository<DetalleVenta, Long> {

}
