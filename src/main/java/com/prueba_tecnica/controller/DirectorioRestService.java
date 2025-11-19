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
import com.prueba_tecnica.service.PersonaRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/personas")
public class DirectorioRestService {


    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping("/store")
    public ResponseEntity<Persona> postMethodName(@RequestBody Persona persona) {
        Persona nuevaPersona = personaRepository.storePersona(persona);        
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
    }

    @GetMapping("/idenficacion/{identificacion}")
    public ResponseEntity<?>findPersonaByIdentificacion(@PathVariable String identificacion) {

        Optional<Persona> persona = personaRepository.findPersonaByIdentificador(identificacion);
        
        return persona.isPresent() ?
            ResponseEntity.ok(persona.get()) :
            ResponseEntity.status(Response.SC_NOT_FOUND).body("Persona no encontrada");
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Persona>> findPersonas(@PathVariable String nombre) {

       Optional<List<Persona>> personas =  personaRepository.findPersonas(nombre);
       return personas.isPresent() ?
            ResponseEntity.ok(personas.get()) :
            ResponseEntity.status(Response.SC_NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{identificacion}")
    public ResponseEntity<?> deletePersona(@PathVariable String identificacion) {

        personaRepository.deletePersonaByIdentificador(identificacion);
        
        return ResponseEntity.ok("Persona eliminada correctamente");
        
    }
}
