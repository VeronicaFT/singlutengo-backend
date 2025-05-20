package com.singlutengo.security;

import com.singlutengo.repository.UsuarioRepository;
import com.singlutengo.entity.Usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro personalizado que se ejecuta una vez por petición.
 * Se encarga de verificar el token JWT en las cabeceras y autenticar al usuario si es válido.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Método que intercepta cada petición HTTP entrante.
     * Si la cabecera Authorization contiene un token JWT válido,
     * autentica al usuario y establece el contexto de seguridad.
     *
     * @param request       petición HTTP entrante
     * @param response      respuesta HTTP
     * @param filterChain   cadena de filtros de Spring Security
     * @throws ServletException en caso de error en la cadena de filtros
     * @throws IOException en caso de error de entrada/salida
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Leer el header Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Si no empieza por "Bearer ", no hacemos nada
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extraer el token
        String token = authHeader.substring(7); // salta "Bearer "
        String email = jwtUtil.extractEmail(token); // recupera el email del token

        // 4. Si hay email y no hay autenticación activa
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Usuario usuario = usuarioRepository.findByEmail(email);

            // 5. Si el token es válido para ese usuario, lo autenticamos manualmente
            if (usuario != null && jwtUtil.isTokenValid(token, usuario.getEmail())) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(usuario, null, null);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 6. Continuamos con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
