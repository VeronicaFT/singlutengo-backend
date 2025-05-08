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
 */
@Service
public class ValoracionServiceImpl implements ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;
    @Autowired
    private EstablecimientoRepository establecimientoRepository;


    @Override
    public List<Valoracion> findAll() {
        return valoracionRepository.findAll();
    }

    @Override
    public Valoracion findById(Long id) {
        Optional<Valoracion> optional = valoracionRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Valoracion save(Valoracion valoracion) {
        // 1. Guardar la nueva valoración
        Valoracion guardada = valoracionRepository.save(valoracion);

        // 2. Calcular la nueva media
        Long establecimientoId = valoracion.getEstablecimiento().getId();
        Double nuevaMedia = valoracionRepository.calcularMediaPorEstablecimientoId(establecimientoId);

        // 3. Actualizar la media en el establecimiento
        Establecimiento establecimiento = establecimientoRepository.findById(establecimientoId).orElse(null);

        if (establecimiento != null) {
            establecimiento.setValoracionMedia(nuevaMedia);
            establecimientoRepository.save(establecimiento);
        }

        return guardada;
    }


    @Override
    public void deleteById(Long id) {
        valoracionRepository.deleteById(id);
    }
    
    @Override
    public List<Valoracion> findByEstablecimientoId(Long establecimientoId) {
        return valoracionRepository.findByEstablecimientoId(establecimientoId);
    }

}
