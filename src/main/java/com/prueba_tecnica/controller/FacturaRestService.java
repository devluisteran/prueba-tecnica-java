package com.prueba_tecnica.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba_tecnica.entity.Factura;
import com.prueba_tecnica.exceptions.ResourceNotFoundException;
import com.prueba_tecnica.service.FacturaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/facturas")
public class FacturaRestService {

    @Autowired
    private FacturaRepository facturaRepository;


    @PostMapping("/store")
    public ResponseEntity<Factura> storeFactura(@RequestBody Factura factura) {
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
