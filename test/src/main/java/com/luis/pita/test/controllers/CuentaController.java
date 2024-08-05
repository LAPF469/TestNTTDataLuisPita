package com.luis.pita.test.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.pita.test.models.ClienteModel;
import com.luis.pita.test.models.CuentaModel;
import com.luis.pita.test.servicies.ClienteService;
import com.luis.pita.test.servicies.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<CuentaModel> getAllCuentas() {
        return cuentaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CuentaModel> getCuentaById(@PathVariable Long id) {
        return cuentaService.findById(id);
    }

    @PostMapping
    public CuentaModel createCuenta(@RequestBody CuentaModel cuenta) {
        if (cuenta.getCliente() != null && cuenta.getCliente().getId() != null) {
            ClienteModel cliente = clienteService.findById(cuenta.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            cuenta.setCliente(cliente);
        }
        return cuentaService.save(cuenta);
    }

    @PutMapping("/{id}")
    public CuentaModel updateCuenta(@PathVariable Long id, @RequestBody CuentaModel cuenta) {
        cuenta.setId(id);
        return cuentaService.save(cuenta);
    }

    @DeleteMapping("/{id}")
    public void deleteCuenta(@PathVariable Long id) {
        cuentaService.delete(id);
    }
}