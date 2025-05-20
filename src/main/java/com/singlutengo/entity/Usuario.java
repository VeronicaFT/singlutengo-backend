package com.singlutengo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

/**
 * Entidad que representa a un usuario registrado en la plataforma.
 * Puede ser un usuario estándar o un administrador.
 */
@Entity
public class Usuario {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del usuario.
     * No puede estar en blanco.
     */
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    /**
     * Dirección de correo electrónico del usuario.
     * Debe ser un email válido y no puede estar vacío.
     */
    @Email(message = "El correo electrónico no es válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

    /**
     * Contraseña del usuario.
     * Se almacena encriptada y no puede estar vacía.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "password")
    private String password;

    /**
     * Tipo de usuario: puede ser "usuario" o "admin".
     * No puede estar vacío.
     */
    @NotBlank(message = "El tipo de usuario es obligatorio")
    private String tipoUsuario;

    /**
     * Fecha en la que se registró el usuario en la plataforma.
     * No puede ser nula.
     */
    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate fechaRegistro;

    // Getters y setters

    /**
     * Devuelve el ID del usuario.
     * @return identificador único
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     * @param id identificador único
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del usuario.
     * @return nombre completo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre nombre completo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el correo electrónico del usuario.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param email dirección de correo válida
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve la contraseña del usuario (encriptada).
     * @return contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password contraseña sin encriptar (se encripta antes de guardar)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve el tipo de usuario ("usuario" o "admin").
     * @return tipo de usuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el tipo de usuario.
     * @param tipoUsuario debe ser "usuario" o "admin"
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Devuelve la fecha de registro del usuario.
     * @return fecha de registro
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del usuario.
     * @param fechaRegistro fecha en formato ISO (yyyy-MM-dd)
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
