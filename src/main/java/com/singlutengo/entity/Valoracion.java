package com.singlutengo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Entidad que representa una valoración de un establecimiento hecha por un usuario.
 */
@Entity
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario que realiza la valoración.
     * No puede estar vacío.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id") // Esta es la foreign key hacia la tabla usuario
    @NotNull(message = "El usuario no puede ser nulo")
    private Usuario usuario;

    /**
     * Establecimiento que está siendo valorado.
     * No puede estar vacío.
     */
    @ManyToOne
    @JoinColumn(name = "establecimiento_id") // Esta es la foreign key hacia la tabla establecimiento
    @NotNull(message = "El establecimiento no puede ser nulo")
    private Establecimiento establecimiento;

    /**
     * Comentario del usuario sobre el establecimiento.
     * No puede estar en blanco.
     */
    @NotBlank(message = "El comentario no puede estar vacío")
    private String comentario;

    /**
     * Puntuación asignada, debe ser entre 1 y 5.
     */
    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    private Integer puntuacion;

    /**
     * Fecha en que se hizo la valoración.
     * No puede ser nula.
     */
    @NotNull(message = "La fecha de comentario es obligatoria")
    private LocalDate fechaComentario;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public LocalDate getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDate fechaComentario) {
        this.fechaComentario = fechaComentario;
    }
}
