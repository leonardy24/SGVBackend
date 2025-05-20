package com.proyectoSGV.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.main.Role;
import com.proyectoSGV.demo.main.Usuarios;
import com.proyectoSGV.demo.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
        Usuarios user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado"); //RESPONDE CON ERROR 401
        }
        
        // El usuario tiene un solo rol
        Role role = user.getRole();

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
        return new User(user.getUsername(), user.getPassword(), Collections.singleton(authority));
        
    }
}