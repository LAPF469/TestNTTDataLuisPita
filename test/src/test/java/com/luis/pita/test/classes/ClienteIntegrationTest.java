package com.luis.pita.test.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.luis.pita.test.models.ClienteModel;
import com.luis.pita.test.models.PersonaModel;
import com.luis.pita.test.repositories.PersonaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PersonaRepository personaRepository;

    @Test
    public void testCreateCliente() {
        PersonaModel persona = new PersonaModel();
        persona.setNombre("Ana Gómez");
        persona.setGenero("F");
        persona.setEdad(30);
        persona.setIdentificacion("12345678");
        persona.setDireccion("Calle Falsa 123");
        persona.setTelefono("1234567890");

        PersonaModel savedPersona = personaRepository.save(persona);

        ClienteModel cliente = new ClienteModel();
        cliente.setContrasenia("password123");
        cliente.setEstado(true);
        cliente.setPersona(savedPersona);

        ResponseEntity<ClienteModel> response = restTemplate.postForEntity("/clientes", cliente, ClienteModel.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ClienteModel savedCliente = response.getBody();
        assertNotNull(savedCliente);
        assertEquals("password123", savedCliente.getContrasenia());
        assertNotNull(savedCliente.getPersona());
        assertEquals("Ana Gómez", savedCliente.getPersona().getNombre());
    }
}
