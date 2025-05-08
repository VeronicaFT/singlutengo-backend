package com.singlutengo.service;

import java.util.List;

import com.singlutengo.entity.Establecimiento;

/**
 * Interfaz para definir las operaciones que se pueden realizar sobre los establecimientos.
 */
public interface EstablecimientoService {

    /**
     * Devuelve todos los establecimientos.
     * @return lista de establecimientos
     */
    List<Establecimiento> findAll();

    /**
     * Busca un establecimiento por su ID.
     * @param id ID del establecimiento
     * @return el establecimiento si se encuentra
     */
    Establecimiento findById(Long id);

    /**
     * Guarda o actualiza un establecimiento.
     * @param establecimiento objeto establecimiento a guardar
     * @return el establecimiento guardado
     */
    Establecimiento save(Establecimiento establecimiento);

    /**
     * Elimina un establecimiento por su ID.
     * @param id ID del establecimiento a eliminar
     */
    void deleteById(Long id);
}
