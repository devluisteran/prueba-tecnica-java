package com.prueba_tecnica.service;

import org.springframework.stereotype.Service;

import com.prueba_tecnica.repository.Directorio;
import com.prueba_tecnica.entity.Persona;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaRepository {

    private final Directorio directorio;

    public PersonaRepository(Directorio directorio){
        this.directorio = directorio;

    }

    public Optional<Persona>findPersonaByIdentificador(String identificacion){
        return directorio.findPersonaByIdentificador(identificacion);
    }

    public Optional<Persona>findPersonaById(Long id){
        return directorio.findPersonaById(id);
    }

    public Optional<List<Persona>> findPersonas(String nombre){
        return directorio.findPersonas(nombre);
    }

    public void deletePersonaByIdentificador(String identificacion){

        directorio.deletePersonaByIdentificador(identificacion);
    }

    public Persona storePersona (Persona persona){
        return directorio.storePersona(persona);
    }
}
