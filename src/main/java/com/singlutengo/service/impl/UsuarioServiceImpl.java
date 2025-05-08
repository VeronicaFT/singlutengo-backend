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
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
    
    	// Encriptar la contraseña antes de guardar
        String contraseñaEncriptada = passwordEncoder().encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);
    	
        return usuarioRepository.save(usuario);
    }
    
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
