package com.proyectoSGV.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoSGV.demo.main.DetallesVentas;



public interface DetallesVentasRepository extends JpaRepository<DetallesVentas,Integer> {

	List<DetallesVentas> findByVentaId(int idventa);
	
}
