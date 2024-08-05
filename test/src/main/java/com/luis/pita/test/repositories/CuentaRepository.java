package com.luis.pita.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luis.pita.test.models.CuentaModel;

public interface CuentaRepository extends JpaRepository<CuentaModel, Long> {
}