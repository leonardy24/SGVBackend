package com.proyectoSGV.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.main.Productos;
import com.proyectoSGV.demo.main.ProductosVentas;
import com.proyectoSGV.demo.main.Venta;
import com.proyectoSGV.demo.repository.VentasRepository;

@Service
public class VentasService {

	@Autowired
	public VentasRepository ventas;
	
	
	public boolean   registrarVenta(Venta venta) {

		//TENGO EL PROBLEMA QUE CUANDO GUARDO UNA VENTA Y GUARDO LOS PRODUCTOS,
		//EN PRODUCTOS NO ME ESTA ASINGNANDO EL ID CORRESPONDINETE A SU VENTA
		
		
		Venta nVenta =  ventas.save(venta);

		
		
		for (Productos producto : venta.getProductos()) {
			producto.setVenta(nVenta);
		}
		
		
		if(nVenta!= null) {
			return true;
		}
		
		
		return false;

	}
	
}
