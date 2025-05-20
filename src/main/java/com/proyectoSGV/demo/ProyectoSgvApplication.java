package com.proyectoSGV.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.proyectoSGV.demo.controller.LoginController;
import com.proyectoSGV.demo.repository.UsuarioRepository;
import com.proyectoSGV.demo.service.ProductoVentasService;
import com.proyectoSGV.demo.service.ProductosExistenciaService;
import com.proyectoSGV.demo.service.UsuariosService;

@SpringBootApplication
public class ProyectoSgvApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProyectoSgvApplication.class, args);
		
		UsuariosService empUsuarios = context.getBean(UsuariosService.class);
		
		ProductosExistenciaService empProducto = context.getBean(ProductosExistenciaService.class);
		
		ProductoVentasService empProductoV = context.getBean(ProductoVentasService.class);
		//System.out.println(empUsuarios.usuariosEmp.findById(1).get().getUsuario());
		
		//System.out.println(empProducto.productosEmp.findById(1));
		//System.out.println(empProductoV.buscarPorCodigo(123456));
		
		//LoginController login = new LoginController();
		
		//login.CrearReporteProductoExistentes(0);
	}

}
