package com.proyectoSGV.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.main.DetallesVentas;
import com.proyectoSGV.demo.main.ProductosVentas;
import com.proyectoSGV.demo.main.Usuarios;
import com.proyectoSGV.demo.main.Venta;
import com.proyectoSGV.demo.repository.ProductoExistenciaRepository;
import com.proyectoSGV.demo.repository.UsuarioRepository;
import com.proyectoSGV.demo.repository.VentasRepository;

@Service
public class VentasService {

	@Autowired
	VentasRepository ventas;

	@Autowired
	ProductoExistenciaRepository proExiRepository;

	@Autowired
	UsuarioRepository userRepo;

	public boolean registrarVenta(Venta venta, String userName) {

		

		venta.setProductos(venta.getProductos());
		venta.setFecha(LocalDateTime.now());
		venta.setUsuario(userRepo.findByUsername(userName));
		System.out.println("usuario"+venta.getUsuario());
		Venta nVenta = ventas.save(venta);

		if (nVenta != null) {

			// RESTO LA EXISTENCIA EN LA BASE DE DATOS
			for (DetallesVentas producto : venta.getProductos()) {

				int numModificados = proExiRepository.descontarStock(producto.getCodigo(), producto.getCantidad());

				if (numModificados < 0) {
					return false;
					// hubo algun error al intentar restar la cantidad de stock en la base de datos
				}

			}

			return true;
		}

		return false;

	}
	
	public List<Venta> findByFechaBetween(LocalDateTime fechaIniTime,LocalDateTime fechaFinTime){
		
		
		return ventas.findByFechaBetween(fechaIniTime,fechaFinTime);
		
	}

}
