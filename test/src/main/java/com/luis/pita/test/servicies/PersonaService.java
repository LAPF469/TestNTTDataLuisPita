package com.luis.pita.test.servicies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luis.pita.test.models.PersonaModel;
import com.luis.pita.test.repositories.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public PersonaModel save(PersonaModel persona) {
        return personaRepository.save(persona);
    }

    public Optional<PersonaModel> findById(Long id) {
        return personaRepository.findById(id);
    }

    public void deleteById(Long id){
        personaRepository.deleteById(id);
    }
}
