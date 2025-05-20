package com.singlutengo.service.impl;

import com.singlutengo.repository.EstablecimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlutengo.entity.Establecimiento;
import com.singlutengo.service.EstablecimientoService;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de establecimiento.
 * Gestiona las operaciones CRUD utilizando el repositorio.
 */
@Service
public class EstablecimientoServiceImpl implements EstablecimientoService {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    /**
     * Obtiene la lista completa de establecimientos registrados.
     * 
     * @return lista de establecimientos
     */
    @Override
    public List<Establecimiento> findAll() {
        return establecimientoRepository.findAll();
    }

    /**
     * Busca un establecimiento por su ID.
     * 
     * @param id identificador único del establecimiento
     * @return el establecimiento si se encuentra, o null si no existe
     */
    @Override
    public Establecimiento findById(Long id) {
        Optional<Establecimiento> optional = establecimientoRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * Guarda un nuevo establecimiento o actualiza uno existente.
     * 
     * @param establecimiento objeto establecimiento a guardar
     * @return el establecimiento guardado
     */
    @Override
    public Establecimiento save(Establecimiento establecimiento) {
        return establecimientoRepository.save(establecimiento);
    }

    /**
     * Elimina un establecimiento por su ID.
     * 
     * @param id identificador del establecimiento a eliminar
     */
    @Override
    public void deleteById(Long id) {
        establecimientoRepository.deleteById(id);
    }
}
