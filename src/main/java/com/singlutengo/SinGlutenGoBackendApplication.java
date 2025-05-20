package com.singlutengo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del proyecto SinGluten&Go.
 * Inicia la aplicación Spring Boot y escanea las entidades JPA en el paquete indicado.
 */
@EntityScan(basePackages = "com.singlutengo.entity")
@SpringBootApplication
public class SinGlutenGoBackendApplication {

    /**
     * Punto de entrada de la aplicación.
     * Lanza el servidor embebido de Spring Boot.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        SpringApplication.run(SinGlutenGoBackendApplication.class, args);
    }
}
