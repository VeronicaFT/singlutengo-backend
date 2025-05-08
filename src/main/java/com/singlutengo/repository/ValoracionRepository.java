package com.singlutengo.repository;

import com.singlutengo.entity.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repositorio para acceder a las valoraciones.
 */
public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {

    /**
     * Calcula la media de las puntuaciones de un establecimiento concreto.
     *
     * @param establecimientoId ID del establecimiento
     * @return la media de puntuaciones
     */
    @Query("SELECT AVG(v.puntuacion) FROM Valoracion v WHERE v.establecimiento.id = :establecimientoId")
    Double calcularMediaPorEstablecimientoId(@Param("establecimientoId") Long establecimientoId);
    
    List<Valoracion> findByEstablecimientoId(Long establecimientoId);

}

