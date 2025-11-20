package com.prueba_tecnica.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba_tecnica.entity.Persona;
import com.prueba_tecnica.exceptions.BadRequestException;
import com.prueba_tecnica.exceptions.ResourceNotFoundException;
import com.prueba_tecnica.service.PersonaRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("api/personas")
public class DirectorioRestService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorioRestService.class);

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping("/store")
    public ResponseEntity<Persona> postMethodName(@RequestBody Persona persona) {
        logger.info("Creando nueva persona: " + persona);

        Optional<Persona> personaFound = personaRepository.findPersonaByIdentificador(persona.getIdentificacion());

        if (personaFound.isPresent()) {
            logger.error("La persona con identificacion " + persona.getIdentificacion() + " ya existe.");
            throw new BadRequestException("La persona con identificacion " + persona.getIdentificacion() + " ya existe.");
        }

        Persona nuevaPersona = personaRepository.storePersona(persona);
        
        if (nuevaPersona == null) {
            throw new RuntimeException("Error al crear la persona");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
    }

    @GetMapping("/idenficacion/{identificacion}")
    public ResponseEntity<?>findPersonaByIdentificacion(@PathVariable String identificacion) {
        logger.info("Buscando persona con identificacion: "+ identificacion);
        Optional<Persona> persona = personaRepository.findPersonaByIdentificador(identificacion);
        if (persona==null) {
            logger.error("Error al buscar persona con identificacion: "+ identificacion);
            throw new ResourceNotFoundException("Persona no encontrada");
        }
        return persona.isPresent() ?
            ResponseEntity.ok(persona.get()) :
            ResponseEntity.status(Response.SC_NOT_FOUND).body("Persona no encontrada");
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Persona>> findPersonas(@PathVariable String nombre) {

       Optional<List<Persona>> personas =  personaRepository.findPersonas(nombre);
       if (personas.isEmpty()) {
            logger.error("Error al buscar persona por nombre: "+ nombre+" valor personas: " + personas);
            throw new ResourceNotFoundException("No se encontraron personas con el nombre proporcionado");
        }
       return personas.isPresent() ?
            ResponseEntity.ok(personas.get()) :
            ResponseEntity.status(Response.SC_NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{identificacion}")
    public ResponseEntity<?> deletePersona(@PathVariable String identificacion) {
        logger.info("Eliminando persona con identificacion: " + identificacion);

        Persona foundPersona = personaRepository.findPersonaByIdentificador(identificacion)
            .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con identificacion: " + identificacion));
        personaRepository.deletePersonaByIdentificador(identificacion);
        
        return ResponseEntity.ok("Persona eliminada correctamente");
        
    }
}
