package com.prueba_tecnica.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba_tecnica.entity.Factura;
import com.prueba_tecnica.entity.Persona;
import com.prueba_tecnica.exceptions.ResourceNotFoundException;
import com.prueba_tecnica.service.FacturaRepository;
import com.prueba_tecnica.service.PersonaRepository;

import jakarta.validation.Valid;

import com.prueba_tecnica.service.FacturaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/facturas")
public class FacturaRestService {

    private static final Logger logger = LoggerFactory.getLogger(FacturaRestService.class);


    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping("/store")
    public ResponseEntity<Factura> storeFactura(@Valid @RequestBody Factura factura) {
        
        Optional<Persona> foundPersona = personaRepository.findPersonaById(factura.getPersona().getId());
        logger.info("Buscando persona con ID: " + factura.getPersona().getId());
        logger.info("persona encontrada " + foundPersona);
        if (foundPersona.isEmpty() || foundPersona.get() == null) {
            throw new ResourceNotFoundException("No se encontró la persona con ID: " + factura.getPersona().getId());
        }
        Factura entity = facturaRepository.storeFactura(factura);        
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<List<Factura>> getMethodName(@PathVariable long id) {

        Optional<List<Factura>> facturas = facturaRepository.findFacturasByPersona(id);
        if(facturas.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron facturas para la persona con ID: " + id);
        }

        return facturas.isPresent() ?
            ResponseEntity.ok(facturas.get()) :
            ResponseEntity.status(Response.SC_NOT_FOUND).build();
    }
    
    
    

}
