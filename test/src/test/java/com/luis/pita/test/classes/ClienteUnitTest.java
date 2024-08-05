package com.luis.pita.test.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.luis.pita.test.models.ClienteModel;
import com.luis.pita.test.models.PersonaModel;

public class ClienteUnitTest {

    @Test
    public void testClienteModel() {
        PersonaModel persona = new PersonaModel();
        persona.setPersonaId(1L);
        persona.setNombre("Ana Gómez");
        persona.setGenero("F");
        persona.setEdad(30);
        persona.setIdentificacion("12345678");
        persona.setDireccion("Calle Falsa 123");
        persona.setTelefono("1234567890");

        ClienteModel cliente = new ClienteModel();
        cliente.setId(1L);
        cliente.setContrasenia("password123");
        cliente.setEstado(true);
        cliente.setPersona(persona);

        assertEquals(1L, cliente.getId());
        assertEquals("password123", cliente.getContrasenia());
        assertTrue(cliente.getEstado());
        assertNotNull(cliente.getPersona());

        PersonaModel clientePersona = cliente.getPersona();
        assertEquals(1L, clientePersona.getPersonaId());
        assertEquals("Ana Gómez", clientePersona.getNombre());
        assertEquals("F", clientePersona.getGenero());
        assertEquals(30, clientePersona.getEdad());
        assertEquals("12345678", clientePersona.getIdentificacion());
        assertEquals("Calle Falsa 123", clientePersona.getDireccion());
        assertEquals("1234567890", clientePersona.getTelefono());
    }
}