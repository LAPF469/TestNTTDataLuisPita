package com.luis.pita.test.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luis.pita.test.models.CuentaModel;
import com.luis.pita.test.models.MovimientoModel;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoModel, Long> {
    List<MovimientoModel> findByCuentaAndFechaBetween(CuentaModel cuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
