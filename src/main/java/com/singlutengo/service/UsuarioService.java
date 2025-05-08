package com.singlutengo.service;

import com.singlutengo.entity.Usuario;
import java.util.List;

/**
 * Interfaz para definir las operaciones que se pueden realizar sobre los usuarios.
 */
public interface UsuarioService {

    /**
     * Devuelve todos los usuarios registrados.
     * @return lista de usuarios
     */
    List<Usuario> findAll();

    /**
     * Devuelve un usuario por su ID.
     * @param id ID del usuario
     * @return el usuario si se encuentra
     */
    Usuario findById(Long id);

    /**
     * Guarda o actualiza un usuario.
     * @param usuario objeto usuario a guardar
     * @return el usuario guardado
     */
    Usuario save(Usuario usuario);

    /**
     * Elimina un usuario por su ID.
     * @param id ID del usuario a eliminar
     */
    void deleteById(Long id);
}
