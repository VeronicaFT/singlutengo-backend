package com.singlutengo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.singlutengo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);

}


