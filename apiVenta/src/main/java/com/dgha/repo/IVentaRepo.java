package com.dgha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgha.model.Venta;

public interface IVentaRepo extends JpaRepository<Venta, Long> {

}
