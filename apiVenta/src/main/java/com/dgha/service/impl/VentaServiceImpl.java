package com.dgha.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgha.exception.EmptyObjectException;
import com.dgha.exception.ModelNotFoundException;
import com.dgha.model.Persona;
import com.dgha.model.Producto;
import com.dgha.model.Venta;
import com.dgha.repo.IDetalleVentaRepo;
import com.dgha.repo.IProductoRepo;
import com.dgha.repo.IVentaRepo;
import com.dgha.service.IPersonaService;
import com.dgha.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepo repo;
	@Autowired
	private IDetalleVentaRepo dvRepo;
	@Autowired
	private IPersonaService personaService;
	@Autowired
	private IProductoRepo productoRepo;
	
	private double importeVenta;

	@Override
	public Venta registrar(Venta t) {
		return repo.save(t);
	}

	@Override
	public Venta modificar(Venta t) {
		return null;
	}

	@Override
	public Optional<Venta> leerPorId(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Venta> listar() {
		return null;
	}

	@Override
	public void eliminar(Long id) {
	}

	private void validarDatosDeVenta(Venta venta) {
		if (venta.getPersona() == null) {
			throw new EmptyObjectException("Debe indicar la persona que realizó la venta");
		}
		if (venta.getPersona().getDni() == null || venta.getPersona().getDni().trim().isEmpty()) {
			throw new ModelNotFoundException("Debe indicar el DNI de la persona que realizó la venta");
		}
		Persona persona = personaService.buscarPorDni(venta.getPersona().getDni());
		if (persona == null) {
			throw new ModelNotFoundException("La persona que realizó la venta no está registrada");
		}
		venta.getPersona().setIdPersona(persona.getIdPersona());
		if (venta.getDetalleVenta() == null || venta.getDetalleVenta().isEmpty()) {
			throw new EmptyObjectException("Debe indicar el detalle de la venta");
		}
		venta.getDetalleVenta().forEach(detalle -> {
			if (detalle.getProducto() == null) {
				throw new EmptyObjectException("Debe indicar el producto vendido");
			}
			if (detalle.getProducto().getIdProducto() == null) {
				throw new ModelNotFoundException("Debe indicar el código del producto vendido");
			}
			Optional<Producto> producto = productoRepo.findById(detalle.getProducto().getIdProducto());
			if (!producto.isPresent()) {
				throw new ModelNotFoundException("El producto a vender no está registrado");
			}
			detalle.getProducto().setPrecio(producto.get().getPrecio());
		});
	}
	
	private void calcularImporteVenta(Venta venta) {
		this.importeVenta = 0d;
		venta.getDetalleVenta().forEach((detalle) -> {
			this.importeVenta += detalle.getProducto().getPrecio().doubleValue() * detalle.getCantidad().intValue();
		});
		venta.setImporte(new Double(importeVenta));
	}

	@Transactional
	@Override
	public Venta registrarVentaConDetalle(Venta venta) {
		validarDatosDeVenta(venta);
		calcularImporteVenta(venta);
		venta.getDetalleVenta().forEach(detalle -> {
			detalle.setVenta(venta);
		});
		repo.save(venta);
		venta.getDetalleVenta().forEach(det -> {
			dvRepo.save(det);
		});
		return venta;
	}
}
