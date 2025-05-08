package com.singlutengo.service;

import com.singlutengo.entity.Valoracion;
import java.util.List;

/**
 * Interfaz para definir las operaciones que se pueden realizar sobre las valoraciones.
 */
public interface ValoracionService {

    /**
     * Devuelve todas las valoraciones.
     * @return lista de valoraciones
     */
    List<Valoracion> findAll();

    /**
     * Devuelve una valoración por su ID.
     * @param id ID de la valoración
     * @return la valoración si se encuentra, o null
     */
    Valoracion findById(Long id);

    /**
     * Guarda o actualiza una valoración.
     * @param valoracion objeto valoración a guardar
     * @return la valoración guardada
     */
    Valoracion save(Valoracion valoracion);

    /**
     * Elimina una valoración por su ID.
     * @param id ID de la valoración a eliminar
     */
    void deleteById(Long id);
    
    
    List<Valoracion> findByEstablecimientoId(Long establecimientoId);

}
