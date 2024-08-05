package com.luis.pita.test.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luis.pita.test.models.ClienteModel;
import com.luis.pita.test.models.CuentaModel;
import com.luis.pita.test.repositories.CuentaRepository;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    public List<CuentaModel> findAll() {
        return cuentaRepository.findAll();
    }

    public Optional<CuentaModel> findById(Long id) {
        return cuentaRepository.findById(id);
    }

    public List<CuentaModel> findByCliente(ClienteModel cliente) {
        return cuentaRepository.findByCliente(cliente);
    }

    public CuentaModel save(CuentaModel cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }
}
