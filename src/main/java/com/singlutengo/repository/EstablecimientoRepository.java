package com.singlutengo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.singlutengo.entity.Establecimiento;

/**
 * Repositorio JPA para la entidad Establecimiento.
 * Proporciona operaciones CRUD básicas como guardar, buscar, listar y eliminar.
 */
public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Long> {
}
