package com.luis.pita.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luis.pita.test.models.PersonaModel;

public interface PersonaRepository extends JpaRepository<PersonaModel, Long> {
}