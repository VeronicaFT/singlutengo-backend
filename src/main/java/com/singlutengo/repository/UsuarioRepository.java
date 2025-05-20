package com.singlutengo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.singlutengo.entity.Usuario;

/**
 * Repositorio JPA para la entidad Usuario.
 * Proporciona operaciones CRUD básicas y consulta personalizada por email.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su dirección de correo electrónico.
     * 
     * @param email email del usuario a buscar
     * @return el usuario correspondiente si existe, o null si no se encuentra
     */
    Usuario findByEmail(String email);
}
