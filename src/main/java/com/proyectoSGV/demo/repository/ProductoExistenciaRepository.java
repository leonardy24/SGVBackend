package com.proyectoSGV.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoSGV.demo.main.ProductosExistencia;



public interface ProductoExistenciaRepository extends JpaRepository<ProductosExistencia,Integer>{

	ProductosExistencia findByCodigo(int codigo);
	
}
