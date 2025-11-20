package com.prueba_tecnica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba_tecnica.entity.Factura;
import com.prueba_tecnica.repository.Ventas;

@Service
public class FacturaRepository {

    private final Ventas ventas;

    public FacturaRepository(Ventas ventas){
        this.ventas = ventas;
    }

    public Factura storeFactura (Factura factura){
        return ventas.storeFactura(factura);
    }

    public Optional<List<Factura>> findFacturasByPersona(Long personaId){
        return ventas.findFacturasByPersona(personaId);
    }
}
