package com.singlutengo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

/**
 * Clase de utilidad para generar y validar tokens JWT.
 */
@Component
public class JwtUtil {

	// Clave secreta fija para firmar los tokens JWT en esta versión de entrega.
	// En producción, esta clave debería almacenarse en una variable de entorno o archivo externo seguro
	private final String SECRET_KEY = "claveSuperSeguraSinGlutenGo_2025_123!!";


    // Duración del token (10 horas)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * Genera un token JWT con el email del usuario.
     *
     * @param email email del usuario autenticado
     * @return token JWT generado
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // quién es el usuario
                .setIssuedAt(new Date()) // cuándo se creó
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // cuándo caduca
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256) // algoritmo y clave secreta
                .compact();
    }

    /**
     * Extrae el email (subject) de un token JWT.
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Verifica si un token es válido.
     */
    public boolean isTokenValid(String token, String userEmail) {
        String email = extractEmail(token);
        return (email.equals(userEmail) && !isTokenExpired(token));
    }

    /**
     * Verifica si un token ha expirado.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrae la fecha de expiración del token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae una "claim" personalizada del token.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    	Claims claims = Jwts.parserBuilder()
    		    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
    		    .build()
    		    .parseClaimsJws(token)
    		    .getBody();

        return claimsResolver.apply(claims);
    }
}
