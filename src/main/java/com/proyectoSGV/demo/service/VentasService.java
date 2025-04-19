package com.proyectoSGV.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.main.Productos;
import com.proyectoSGV.demo.main.ProductosVentas;
import com.proyectoSGV.demo.main.Venta;
import com.proyectoSGV.demo.repository.ProductoExistenciaRepository;
import com.proyectoSGV.demo.repository.VentasRepository;

@Service
public class VentasService {

	@Autowired
	 VentasRepository ventas;
	
	@Autowired
	ProductoExistenciaRepository proExiRepository;

	public boolean registrarVenta(Venta venta) {

		// TENGO EL PROBLEMA QUE CUANDO GUARDO UNA VENTA Y GUARDO LOS PRODUCTOS,
		// EN PRODUCTOS NO ME ESTA ASINGNANDO EL ID CORRESPONDINETE A SU VENTA

		venta.setProductos(venta.getProductos());
		Venta nVenta = ventas.save(venta);
		/*
		//RESTO LA EXISTENCIA EN LA BASE DE DATOS
		for (Productos producto : venta.getProductos()) {
			
			
			
			
			int numModificados = proExiRepository.descontarStock(producto.getCodigo(), producto.getCantidad());
			
			if(numModificados<0) {
				return false;
				//hubo algun error al intentar restar la cantidad de stock en la base de datos
			}
			
		}
		*/

		if (nVenta != null) {
			return true;
		}

		return false;

	}

}
