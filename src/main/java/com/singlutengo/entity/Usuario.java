package com.singlutengo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;


/**
 * Entidad que representa a un usuario registrado en la plataforma.
 */

@Entity
public class Usuario {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    @NotBlank(message = "El nombre es obligatorio")
        private String nombre;
	    
	    @Email (message = "El correo electrónico no es válido")
	    @NotBlank(message = "El correo electrónico es obligatorio")
	    private String email;
	    
	    @NotBlank(message = "La contraseña es obligatoria")
	    @Column(name = "password")
	    private String password;
	    
	    @NotBlank(message = "El tipo de usuario es obligatorio")
	    private String tipoUsuario; // usuario o admin
	    
	    @NotNull(message = "La fecha de registro es obligatoria")
	    private LocalDate fechaRegistro;

	    // Getters y setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getTipoUsuario() {
	        return tipoUsuario;
	    }

	    public void setTipoUsuario(String tipoUsuario) {
	        this.tipoUsuario = tipoUsuario;
	    }

	    public LocalDate getFechaRegistro() {
	        return fechaRegistro;
	    }

	    public void setFechaRegistro(LocalDate fechaRegistro) {
	        this.fechaRegistro = fechaRegistro;
	    }
	

}
