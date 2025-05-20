package com.singlutengo.service.impl;

import com.singlutengo.entity.Usuario;
import com.singlutengo.repository.UsuarioRepository;
import com.singlutengo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Implementación del servicio de usuarios.
 * Gestiona las operaciones CRUD y se encarga de encriptar la contraseña antes de guardar.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Devuelve todos los usuarios registrados en la base de datos.
     * 
     * @return lista de usuarios
     */
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario por su ID.
     * 
     * @param id identificador del usuario
     * @return el usuario si se encuentra, o null si no existe
     */
    @Override
    public Usuario findById(Long id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * Guarda un nuevo usuario o actualiza uno existente.
     * Antes de guardar, encripta la contraseña con BCrypt.
     * 
     * @param usuario objeto usuario a guardar
     * @return el usuario guardado
     */
    @Override
    public Usuario save(Usuario usuario) {
        String contraseñaEncriptada = passwordEncoder().encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);
        return usuarioRepository.save(usuario);
    }

    /**
     * Crea un codificador de contraseñas basado en BCrypt.
     * 
     * @return instancia de PasswordEncoder
     */
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Elimina un usuario por su ID.
     * 
     * @param id identificador del usuario a eliminar
     */
    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
