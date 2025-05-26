package com.proyectoSGV.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.main.DetallesVentas;
import com.proyectoSGV.demo.repository.DetallesVentasRepository;

@Service
public class DetallesVentasService {

	@Autowired
	DetallesVentasRepository detallesVentas;

	public List<DetallesVentas> ventas(int idVenta) {

		return detallesVentas.findByVentaId(idVenta);
	}

}
