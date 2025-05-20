package com.singlutengo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * Entidad que representa una valoración de un establecimiento hecha por un usuario.
 * Cada valoración incluye un comentario, una puntuación y una fecha.
 */
@Entity
public class Valoracion {

    /**
     * Identificador único de la valoración.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario que realiza la valoración.
     * No puede ser nulo.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "El usuario no puede ser nulo")
    private Usuario usuario;

    /**
     * Establecimiento que está siendo valorado.
     * No puede ser nulo.
     */
    @ManyToOne
    @JoinColumn(name = "establecimiento_id")
    @NotNull(message = "El establecimiento no puede ser nulo")
    private Establecimiento establecimiento;

    /**
     * Comentario del usuario sobre el establecimiento.
     * No puede estar en blanco.
     */
    @NotBlank(message = "El comentario no puede estar vacío")
    private String comentario;

    /**
     * Puntuación asignada por el usuario.
     * Debe ser un número entre 1 y 5.
     */
    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    private Integer puntuacion;

    /**
     * Fecha en la que se realizó la valoración.
     * No puede ser nula.
     */
    @NotNull(message = "La fecha de comentario es obligatoria")
    private LocalDate fechaComentario;

    // Getters y setters

    /**
     * Devuelve el ID de la valoración.
     * @return identificador único
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la valoración.
     * @param id identificador único
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el usuario que hizo la valoración.
     * @return objeto Usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realiza la valoración.
     * @param usuario entidad Usuario asociada
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve el establecimiento que fue valorado.
     * @return entidad Establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * Establece el establecimiento que fue valorado.
     * @param establecimiento entidad Establecimiento
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * Devuelve el comentario escrito por el usuario.
     * @return texto del comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el comentario de la valoración.
     * @param comentario texto del comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Devuelve la puntuación asignada al establecimiento.
     * @return puntuación entre 1 y 5
     */
    public Integer getPuntuacion() {
        return puntuacion;
    }

    /**
     * Establece la puntuación de la valoración.
     * @param puntuacion número entre 1 y 5
     */
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Devuelve la fecha de la valoración.
     * @return fecha en formato yyyy-MM-dd
     */
    public LocalDate getFechaComentario() {
        return fechaComentario;
    }

    /**
     * Establece la fecha en que se hizo la valoración.
     * @param fechaComentario fecha de creación
     */
    public void setFechaComentario(LocalDate fechaComentario) {
        this.fechaComentario = fechaComentario;
    }
}
