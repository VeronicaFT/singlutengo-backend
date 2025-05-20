package com.singlutengo.controller;

import com.singlutengo.dto.LoginRequest;
import com.singlutengo.entity.Usuario;
import com.singlutengo.repository.UsuarioRepository;
import com.singlutengo.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de autenticación.
 * Gestiona el login y devuelve un token JWT si las credenciales son correctas.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Realiza el proceso de login de un usuario.
     * Verifica si el email y la contraseña son correctos, y si lo son:
     * - Genera un token JWT usando el email como identificador.
     * - Devuelve un objeto JSON con el token y los datos principales del usuario.
     * 
     * En caso de error (usuario no encontrado o contraseña incorrecta), devuelve un mensaje de error simple.
     *
     * @param loginRequest objeto con email y contraseña recibidos desde el frontend
     * @return JSON con el token y datos del usuario, o un mensaje de error si las credenciales son inválidas
     */
    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario == null) {
            return "Usuario no encontrado";
        }

        if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            // 1. Generar token con el email
            String token = jwtUtil.generateToken(usuario.getEmail());

            // 2. Devolver token + info básica del usuario
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("nombre", usuario.getNombre());
            response.put("tipoUsuario", usuario.getTipoUsuario());
            response.put("id", usuario.getId());

            return response;
        } else {
            return "Contraseña incorrecta";
        }
    }
}
