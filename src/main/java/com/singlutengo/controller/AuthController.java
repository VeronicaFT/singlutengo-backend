package com.singlutengo.controller;

import com.singlutengo.dto.LoginRequest;
import com.singlutengo.entity.Usuario;
import com.singlutengo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Endpoint de login básico
     * Recibe email y contraseña, y devuelve el usuario si es válido
     */
    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        // 1. Buscar el usuario por email
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario == null) {
            return "Usuario no encontrado";
           
        }
     
        //prueba contraseña administrador correcta
        System.out.println("➡️ Resultado de comparación manual: " +
    new BCryptPasswordEncoder().matches("admin123", "$2a$10$cOS5N/hb4aMdZWfO1q7U8OAD2xBAuZF4cqCcVaNoB6wN0kQ1EMpKW"));


    
        // 2. Verificar la contraseña con BCrypt
        if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            //Devuelve el usuario completo (Angular podrá leer su rol, nombre, etc.)
            return usuario;
        } else {
            return "Contraseña incorrecta";
        }
    }
     //pruebas de contraseñas encriptadas 
    @GetMapping("/test-encriptar")
    public String generarHashDeAdmin() {
        String rawPassword = "admin123";
        String encoded = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println("👉 Encriptada: " + encoded);
        return encoded;
    }

}
