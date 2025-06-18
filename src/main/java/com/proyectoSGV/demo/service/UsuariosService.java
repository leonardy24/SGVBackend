package com.proyectoSGV.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.proyectoSGV.demo.ProyectoSgvApplication;
import com.proyectoSGV.demo.main.Role;
import com.proyectoSGV.demo.main.UsuDTO;
import com.proyectoSGV.demo.main.Usuarios;
import com.proyectoSGV.demo.repository.UsuarioRepository;

@Service
public class UsuariosService {

    private final ProyectoSgvApplication proyectoSgvApplication;

	@Autowired
	public UsuarioRepository usuariosEmp;//lo dejo publico para pruebas
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;


    UsuariosService(ProyectoSgvApplication proyectoSgvApplication) {
        this.proyectoSgvApplication = proyectoSgvApplication;
    }
	 
	 
	public Usuarios usuExiste(String usuario) {
		
		Usuarios usu = usuariosEmp.findByUsername(usuario);
		if(usu!=null) {
			return usu;
		}
		
		return null;
	}
	
	public Usuarios saveUser(Usuarios user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        
     // Suponiendo que solo los usuarios normales se registran aquí, puedes establecer el rol "USER" por defecto
     //   Role userRole = new Role("USER");
      //  user.getRoles().add(userRole);
        
       
        return usuariosEmp.save(user);
        
    }
	
	 public Usuarios findByUsername(String username) {
	        return usuariosEmp.findByUsername(username);
	    }
	    
	 public Usuarios authenticate(String username, String password) {
	    	Usuarios user = usuariosEmp.findByUsername(username);
	        if (user == null) return null; // CAMBIENLO DE RETORNA BOOLEAN 
	        
	        if(!passwordEncoder.matches(password, user.getPassword())) return null;
	        return user;
	    }

	public boolean actualizarUsuario(UsuDTO usuarioActualizado) {
		
		Optional<Usuarios> usuarioOptional = usuariosEmp.findById(usuarioActualizado.getId());
		Role rol = new Role();
		
		
		
		if(usuarioOptional.isPresent()) {
			
			if(usuarioActualizado.getRol().equals("ADMIN")) {
				rol.setId(2);
				rol.setName("ADMIN");
				
			}else {
				rol.setId(1);
				rol.setName("USER");
				
			}
			
			
			Usuarios usuario = usuarioOptional.get();
			
			//usuario.setUsername(usuarioActualizado.getNombre());
			usuario.setRole(rol);
			
			System.out.println(usuario.getRole());
			usuariosEmp.save(usuario);
			
			return true;
		}
		return false;
	}
	
	
}
