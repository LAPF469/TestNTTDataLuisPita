package com.luis.pita.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luis.pita.test.models.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
