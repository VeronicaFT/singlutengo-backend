package com.singlutengo.controller;

import com.singlutengo.entity.Establecimiento;
import com.singlutengo.service.EstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

/**
 * Controlador REST para gestionar los establecimientos.
 * Expone endpoints para consultar, guardar y eliminar establecimientos.
 */
@RestController // Marca esta clase como un controlador REST
@RequestMapping("/api/establecimientos") // Ruta base para todas las operaciones
public class EstablecimientoController {

    @Autowired // Spring inyecta automáticamente el servicio
    private EstablecimientoService establecimientoService;

    /**
     * Obtiene la lista de todos los establecimientos.
     * URL: GET /api/establecimientos
     * 
     * @return lista de establecimientos
     */
    @GetMapping
    public List<Establecimiento> getAll() {
        return establecimientoService.findAll();
    }

    /**
     * Obtiene un establecimiento por su ID.
     * URL: GET /api/establecimientos/{id}
     *
     * @param id ID del establecimiento
     * @return el establecimiento correspondiente
     */
    @GetMapping("/{id}")
    public Establecimiento getById(@PathVariable Long id) {
        return establecimientoService.findById(id);
    }

    /**
     * Guarda un nuevo establecimiento o actualiza uno existente.
     * URL: POST /api/establecimientos
     *
     * @param establecimiento objeto JSON recibido desde el frontend o Postman
     * @return el establecimiento guardado
     */
    @PostMapping
    public Establecimiento save(@Valid @RequestBody Establecimiento establecimiento) {
        return establecimientoService.save(establecimiento);
    }

    /**
     * Elimina un establecimiento por su ID.
     * URL: DELETE /api/establecimientos/{id}
     *
     * @param id ID del establecimiento a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        establecimientoService.deleteById(id);
    }
}
