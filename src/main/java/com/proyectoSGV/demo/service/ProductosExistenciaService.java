package com.proyectoSGV.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.main.ProductosExistencia;
import com.proyectoSGV.demo.repository.ProductoExistenciaRepository;

@Service
public class ProductosExistenciaService {

	
	@Autowired
	public ProductoExistenciaRepository productosEmp; 
	
	
	
	public ProductosExistencia buscarPorCodigo(int codigo) {
		
		
		ProductosExistencia producto = productosEmp.findByCodigo(codigo);
		
		
		
		return producto;
		
	}
	
	
	
}
