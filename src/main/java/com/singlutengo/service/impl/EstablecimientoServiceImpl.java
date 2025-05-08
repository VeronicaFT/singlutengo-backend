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
 */
@Service
public class EstablecimientoServiceImpl implements EstablecimientoService {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Override
    public List<Establecimiento> findAll() {
        return establecimientoRepository.findAll();
    }

    @Override
    public Establecimiento findById(Long id) {
        Optional<Establecimiento> optional = establecimientoRepository.findById(id);
        return optional.orElse(null); // si no lo encuentra, devuelve null
    }

    @Override
    public Establecimiento save(Establecimiento establecimiento) {
        return establecimientoRepository.save(establecimiento);
    }

    @Override
    public void deleteById(Long id) {
        establecimientoRepository.deleteById(id);
    }
}
