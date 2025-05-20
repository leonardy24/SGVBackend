package com.proyectoSGV.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.proyectoSGV.demo.main.Usuarios;
import com.proyectoSGV.demo.repository.UsuarioRepository;

@Service
public class UsuariosService {

	@Autowired
	public UsuarioRepository usuariosEmp;//lo dejo publico para pruebas
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 
	public boolean usuExiste(String usuario, String pass) {
		
		if(usuario.equals("leo") && pass.equals("123")) {
			return true;
		}
		
		return false;
	}
	
	public void saveUser(Usuarios user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        
     // Suponiendo que solo los usuarios normales se registran aquí, puedes establecer el rol "USER" por defecto
     //   Role userRole = new Role("USER");
      //  user.getRoles().add(userRole);
        
       
        usuariosEmp.save(user);
        
    }
	
	 public Usuarios findByUsername(String username) {
	        return usuariosEmp.findByUsername(username);
	    }
	    
	 public boolean authenticate(String username, String password) {
	    	Usuarios user = usuariosEmp.findByUsername(username);
	        if (user == null) return false;
	        return passwordEncoder.matches(password, user.getPassword());
	    }
	
	
}
