package com.singlutengo.service.impl;

import com.singlutengo.repository.EstablecimientoRepository;
import com.singlutengo.entity.Establecimiento;
import com.singlutengo.entity.Valoracion;
import com.singlutengo.repository.ValoracionRepository;
import com.singlutengo.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de valoraciones.
 * Gestiona las operaciones CRUD y actualiza automáticamente
 * la media de valoraciones de cada establecimiento.
 */
@Service
public class ValoracionServiceImpl implements ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    /**
     * Obtiene la lista completa de valoraciones registradas.
     *
     * @return lista de valoraciones
     */
    @Override
    public List<Valoracion> findAll() {
        return valoracionRepository.findAll();
    }

    /**
     * Busca una valoración por su ID.
     *
     * @param id identificador de la valoración
     * @return la valoración encontrada, o null si no existe
     */
    @Override
    public Valoracion findById(Long id) {
        Optional<Valoracion> optional = valoracionRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * Guarda una nueva valoración y actualiza la media del establecimiento correspondiente.
     *
     * @param valoracion objeto valoración a guardar
     * @return la valoración guardada
     */
    @Override
    public Valoracion save(Valoracion valoracion) {
        // 1. Guardar la nueva valoración
        Valoracion guardada = valoracionRepository.save(valoracion);

        // 2. Calcular la nueva media de valoraciones para el establecimiento
        Long establecimientoId = valoracion.getEstablecimiento().getId();
        Double nuevaMedia = valoracionRepository.calcularMediaPorEstablecimientoId(establecimientoId);

        // 3. Actualizar el campo valoracionMedia del establecimiento
        Establecimiento establecimiento = establecimientoRepository.findById(establecimientoId).orElse(null);

        if (establecimiento != null) {
            establecimiento.setValoracionMedia(nuevaMedia);
            establecimientoRepository.save(establecimiento);
        }

        return guardada;
    }

    /**
     * Elimina una valoración por su ID.
     *
     * @param id identificador de la valoración a eliminar
     */
    @Override
    public void deleteById(Long id) {
        valoracionRepository.deleteById(id);
    }

    /**
     * Obtiene todas las valoraciones asociadas a un establecimiento específico.
     *
     * @param establecimientoId ID del establecimiento
     * @return lista de valoraciones del establecimiento
     */
    @Override
    public List<Valoracion> findByEstablecimientoId(Long establecimientoId) {
        return valoracionRepository.findByEstablecimientoId(establecimientoId);
    }
}
