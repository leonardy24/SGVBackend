package com.proyectoSGV.demo.service;

import java.util.Optional;

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
	
	public boolean actualizarProducto(ProductosExistencias productoActualizado) {
		
		ProductosExistencias producActualizado = productosEmp.save(productoActualizado);
		
		if(producActualizado != null) {
			return true;
		}
		
		
		return  false;
	}
	
	public boolean eliminarProductoExistencia(int id) {
		
		Optional<ProductosExistencias> producto = productosEmp.findById(id);
		
		if(producto.isEmpty()) {
			return false;
		}
		
		productosEmp.deleteById(id);
		
		return true;
		
	}

}
