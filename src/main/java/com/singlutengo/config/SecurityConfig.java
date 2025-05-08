package com.singlutengo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.http.HttpMethod;

/**
 * Configuración de seguridad de la aplicación.
 * Permite el acceso libre a las rutas necesarias durante el desarrollo.
 */
@Configuration
public class SecurityConfig {
	
	// Durante desarrollo se deja acceso libre!
	// ¡¡¡¡¡Cambiar más adelante cuando se implemente seguridad con JWT!!!!!


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para facilitar pruebas desde Postman
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers(HttpMethod.POST, "/api/establecimientos/**").permitAll()	
                .requestMatchers("/api/establecimientos/**").permitAll() // permite acceso sin login
                .requestMatchers("/api/valoraciones/**").permitAll()
                .requestMatchers("/api/usuarios/**").permitAll()
                .requestMatchers("/auth/**").permitAll() 
                .anyRequest().permitAll() 
            )

        
        .cors(withDefaults());

        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Angular
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
