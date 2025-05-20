package com.singlutengo.config;

import com.singlutengo.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.http.HttpMethod;

import java.util.List;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuración de seguridad con filtro JWT activado.
 * Define qué rutas están permitidas sin autenticación y cómo se aplica el filtro de JWT.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Define la cadena de filtros de seguridad para las peticiones HTTP.
     * - Desactiva CSRF.
     * - Permite el acceso sin autenticación a ciertas rutas.
     * - Protege todas las demás rutas.
     * - Añade el filtro JWT antes del filtro de autenticación por defecto.
     *
     * @param http objeto de configuración HTTP de Spring Security
     * @return instancia de SecurityFilterChain configurada
     * @throws Exception si hay un error en la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Permitimos login y registro
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

                // Todo lo demás requiere estar autenticado
                .requestMatchers(HttpMethod.GET, "/api/establecimientos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/valoraciones/**").permitAll()
                .anyRequest().authenticated()
            )
            .cors(withDefaults());

        // Añadimos el filtro antes del filtro por defecto de Spring Security
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Proporciona el AuthenticationManager a partir de la configuración actual.
     * Este método permite usar autenticación manual si se necesita más adelante.
     *
     * @param config configuración de autenticación proporcionada por Spring
     * @return AuthenticationManager configurado
     * @throws Exception si ocurre un error al obtener el manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura el CORS para permitir peticiones desde el frontend en localhost:4200.
     * Se permiten métodos comunes y todas las cabeceras.
     *
     * @return fuente de configuración de CORS aplicada a todas las rutas
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
