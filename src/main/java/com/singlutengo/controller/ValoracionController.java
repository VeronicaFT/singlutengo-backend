package com.singlutengo.controller;

import com.singlutengo.entity.Valoracion;
import com.singlutengo.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;


/**
 * Controlador REST para gestionar las valoraciones.
 * Permite consultar, crear y eliminar valoraciones.
 */
@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    /**
     * Obtiene todas las valoraciones.
     * URL: GET /api/valoraciones
     * 
     * @return lista de valoraciones
     */
    @GetMapping
    public List<Valoracion> getAll() {
        return valoracionService.findAll();
    }

    /**
     * Obtiene una valoración por su ID.
     * URL: GET /api/valoraciones/{id}
     * 
     * @param id ID de la valoración
     * @return valoración correspondiente
     */
    @GetMapping("/{id}")
    public Valoracion getById(@PathVariable Long id) {
        return valoracionService.findById(id);
    }

    /**
     * Crea o actualiza una valoración.
     * URL: POST /api/valoraciones
     * 
     * @param valoracion objeto JSON recibido
     * @return valoración guardada
     */
    @PostMapping
    public Valoracion save(@Valid @RequestBody Valoracion valoracion) {
        return valoracionService.save(valoracion);
    }

    /**
     * Elimina una valoración por ID.
     * URL: DELETE /api/valoraciones/{id}
     * 
     * @param id ID de la valoración a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        valoracionService.deleteById(id);
    }
    
    @GetMapping("/establecimiento/{id}")
    public List<Valoracion> getByEstablecimientoId(@PathVariable Long id) {
        return valoracionService.findByEstablecimientoId(id);
    }

}
