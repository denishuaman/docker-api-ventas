package com.dgha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgha.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Long> {

}
