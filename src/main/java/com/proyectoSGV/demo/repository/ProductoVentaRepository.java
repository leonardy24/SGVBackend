package com.proyectoSGV.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyectoSGV.demo.main.ProductosVentas;



public interface ProductoVentaRepository extends JpaRepository<ProductosVentas,Integer>{

	
	ProductosVentas findByCodigo(long codigo);
	
	
	
}
