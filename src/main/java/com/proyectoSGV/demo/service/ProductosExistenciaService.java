package com.proyectoSGV.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.main.ProductosExistencias;
import com.proyectoSGV.demo.repository.ProductoExistenciaRepository;

@Service
public class ProductosExistenciaService {

	
	@Autowired
	public ProductoExistenciaRepository productosEmp; 
	
	
	
	public ProductosExistencias buscarPorCodigo(int codigo) {
		
		
		ProductosExistencias producto = productosEmp.findByCodigo(codigo);
		
		
		
		return producto;
		
	}
	
	
	
	
	
	
}
