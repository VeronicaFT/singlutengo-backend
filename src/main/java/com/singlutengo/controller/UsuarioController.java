package com.singlutengo.controller;

import com.singlutengo.entity.Usuario;
import com.singlutengo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar los usuarios.
 * Permite consultar, guardar y eliminar usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtiene todos los usuarios registrados.
     * URL: GET /api/usuarios
     * 
     * @return lista de usuarios
     */
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    /**
     * Obtiene un usuario por su ID.
     * URL: GET /api/usuarios/{id}
     * 
     * @param id ID del usuario
     * @return usuario correspondiente si existe
     */
    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    /**
     * Crea o actualiza un usuario.
     * URL: POST /api/usuarios
     * 
     * @param usuario objeto JSON recibido
     * @return usuario guardado
     */
    @PostMapping
    public Usuario save(@Valid @RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    /**
     * Elimina un usuario por ID.
     * URL: DELETE /api/usuarios/{id}
     * 
     * @param id ID del usuario a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
