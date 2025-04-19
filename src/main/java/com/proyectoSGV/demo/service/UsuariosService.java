package com.proyectoSGV.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.repository.UsuarioRepository;

@Service
public class UsuariosService {

	@Autowired
	public UsuarioRepository usuariosEmp;//lo dejo publico para pruebas
	
	
	public boolean usuExiste(String usuario, String pass) {
		
		
		if(usuario.equals("leo") && pass.equals("123")) {
			return true;
		}
		
		return false;
	}
	
	
}
