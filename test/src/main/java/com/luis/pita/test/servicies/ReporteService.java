package com.luis.pita.test.servicies;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luis.pita.test.exceptions.ResourceNotFoundException;
import com.luis.pita.test.models.ClienteModel;
import com.luis.pita.test.models.CuentaModel;
import com.luis.pita.test.models.EstadoCuentaModel;
import com.luis.pita.test.models.MovimientoModel;

@Service
public class ReporteService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService;

    public List<EstadoCuentaModel> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
        ClienteModel cliente = clienteService.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con id " + clienteId + " no fue encontrado."));

        List<CuentaModel> cuentas = cuentaService.findByCliente(cliente);

        return cuentas.stream().flatMap(cuenta -> {
            LocalDateTime fechaInicioDateTime = fechaInicio.atStartOfDay();
            LocalDateTime fechaFinDateTime = fechaFin.atTime(23, 59, 59);

            List<MovimientoModel> movimientos = movimientoService.findByCuentaAndFechaBetween(cuenta, fechaInicioDateTime, fechaFinDateTime);
            return movimientos.stream().map(movimiento -> {
                EstadoCuentaModel dto = new EstadoCuentaModel();
                dto.setFecha(movimiento.getFecha());
                dto.setCliente(cliente.getPersona().getNombre());
                dto.setNumeroCuenta(cuenta.getNumeroCuenta());
                dto.setTipo(cuenta.getTipoCuenta());
                dto.setSaldoInicial(cuenta.getSaldoInicial());
                dto.setEstado(cuenta.getEstado());
                dto.setMovimiento(movimiento.getValor());
                dto.setSaldoDisponible(movimiento.getSaldo());
                return dto;
            });
        }).collect(Collectors.toList());
    }
}
