package com.luis.pita.test.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.pita.test.exceptions.ResourceNotFoundException;
import com.luis.pita.test.models.ClienteModel;
import com.luis.pita.test.models.ErrorResponse;
import com.luis.pita.test.models.PersonaModel;
import com.luis.pita.test.servicies.ClienteService;
import com.luis.pita.test.servicies.PersonaService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<ClienteModel> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ClienteModel> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ClienteModel> createCliente(@RequestBody ClienteModel cliente) {
        PersonaModel persona;

        if (cliente.getPersona().getPersonaId() != null) {
            persona = personaService.findById(cliente.getPersona().getPersonaId())
                    .orElseGet(() -> personaService.save(cliente.getPersona()));
        } else {
            persona = personaService.save(cliente.getPersona());
        }
        cliente.setPersona(persona);

        ClienteModel savedCliente = clienteService.save(cliente);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long id, @RequestBody ClienteModel cliente) {
        ClienteModel existingCliente = clienteService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con id " + id + " no fue encontrado."));

        PersonaModel personaDetails = cliente.getPersona();
        PersonaModel existingPersona = personaService.findById(personaDetails.getPersonaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                "Persona con id " + personaDetails.getPersonaId() + " no fue encontrado."));

        // Actualiza los detalles de la persona
        existingPersona.setNombre(personaDetails.getNombre());
        existingPersona.setGenero(personaDetails.getGenero());
        existingPersona.setEdad(personaDetails.getEdad());
        existingPersona.setIdentificacion(personaDetails.getIdentificacion());
        existingPersona.setDireccion(personaDetails.getDireccion());
        existingPersona.setTelefono(personaDetails.getTelefono());
        personaService.save(existingPersona);

        // Actualiza los detalles del cliente
        existingCliente.setContrasenia(cliente.getContrasenia());
        existingCliente.setEstado(cliente.getEstado());
        existingCliente.setPersona(existingPersona);

        ClienteModel updatedCliente = clienteService.save(existingCliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        ClienteModel cliente = clienteService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con id " + id + " no fue encontrado."));

        PersonaModel persona = cliente.getPersona();
        if (persona != null) {
            clienteService.deleteById(cliente.getId());
            personaService.deleteById(persona.getPersonaId());
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse error = new ErrorResponse("BAD_REQUEST", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
