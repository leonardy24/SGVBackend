package com.proyectoSGV.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoSGV.demo.main.Venta;

public interface VentasRepository extends JpaRepository<Venta, Integer> {

	
	List<Venta> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
}
