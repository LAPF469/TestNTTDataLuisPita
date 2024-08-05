package com.luis.pita.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luis.pita.test.models.MovimientoModel;

public interface MovimientoRepository extends JpaRepository<MovimientoModel, Long> {
}