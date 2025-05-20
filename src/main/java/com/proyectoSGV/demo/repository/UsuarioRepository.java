package com.proyectoSGV.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoSGV.demo.main.Usuarios;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios,Integer> {
	Usuarios findByUsername(String username);
}
