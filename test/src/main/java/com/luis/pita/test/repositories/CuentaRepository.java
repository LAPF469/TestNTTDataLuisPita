package com.luis.pita.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luis.pita.test.models.ClienteModel;
import com.luis.pita.test.models.CuentaModel;

public interface CuentaRepository extends JpaRepository<CuentaModel, Long> {
    List<CuentaModel> findByCliente(ClienteModel cliente);
}