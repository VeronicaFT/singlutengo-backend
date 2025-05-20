package com.singlutengo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Entidad que representa un establecimiento registrado en la plataforma.
 * Puede ser un restaurante, cafetería, heladería, etc., que ofrece opciones sin gluten.
 */
@Entity
public class Establecimiento {

    /**
     * Identificador único del establecimiento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del establecimiento.
     * No puede estar en blanco.
     */
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    /**
     * Dirección o ubicación del establecimiento.
     * No puede estar en blanco.
     */
    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;

    /**
     * Breve descripción del lugar y su oferta sin gluten.
     * No puede estar en blanco.
     */
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    /**
     * Valoración media del establecimiento, calculada a partir de las valoraciones de los usuarios.
     * Puede ser nula si aún no hay valoraciones.
     */
    private Double valoracionMedia;

    // Getters y setters

    /**
     * Devuelve el ID del establecimiento.
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del establecimiento.
     * @param id identificador único
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del establecimiento.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del establecimiento.
     * @param nombre nombre del local
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la ubicación del establecimiento.
     * @return ubicación (dirección)
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación del establecimiento.
     * @param ubicacion dirección o ciudad
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Devuelve la descripción del establecimiento.
     * @return texto descriptivo
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del establecimiento.
     * @param descripcion texto descriptivo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la valoración media actual del establecimiento.
     * @return puntuación promedio o null si no hay valoraciones
     */
    public Double getValoracionMedia() {
        return valoracionMedia;
    }

    /**
     * Establece la valoración media del establecimiento.
     * @param valoracionMedia media de puntuaciones
     */
    public void setValoracionMedia(Double valoracionMedia) {
        this.valoracionMedia = valoracionMedia;
    }
}
