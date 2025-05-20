package com.proyectoSGV.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoSGV.demo.main.ProductosExistencias;



public interface ProductoExistenciaRepository extends JpaRepository<ProductosExistencias,Integer>{

	ProductosExistencias findByCodigo(long codigo);
	
	 @Modifying
	 @Transactional
	 @Query("UPDATE ProductosExistencias  p SET p.cantidad = p.cantidad - :cantidad WHERE p.codigo = :codigo AND p.cantidad >= :cantidad")
	int descontarStock(@Param("codigo") long codigo, @Param("cantidad") int cantidad);
	
	 
	 List<ProductosExistencias> findAll();

}
