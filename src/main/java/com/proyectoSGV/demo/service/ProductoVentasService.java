package com.proyectoSGV.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.proyectoSGV.demo.main.ProductosVentas;
import com.proyectoSGV.demo.repository.ProductoVentaRepository;

@Service
public class ProductoVentasService {

	@Autowired
	public ProductoVentaRepository productoVentas;

	public ProductosVentas buscarPorCodigo(long codigo) {

		ProductosVentas productoV = productoVentas.findByCodigo(codigo);

		
		return productoV;

	}
}
