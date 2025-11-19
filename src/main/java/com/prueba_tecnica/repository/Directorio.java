package com.prueba_tecnica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.prueba_tecnica.entity.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class Directorio {

    @PersistenceContext
    private EntityManager entityManager;

    public Persona storePersona(Persona persona){
        entityManager.persist(persona);
        return persona;
    }

    public Optional<Persona> findPersonaByIdentificador(String identificacion){
        Persona persona = entityManager.createQuery(
            "SELECT p FROM Persona p WHERE p.identificacion = :identificacion", Persona.class)
            .setParameter("identificacion", identificacion)
            .getResultStream()
            .findFirst()
            .orElse(null);

        return Optional.ofNullable(persona);
    }

    public Optional<List<Persona>> findPersonas(String nombre){
        List<Persona> personas = entityManager.createQuery(
            "SELECT p FROM Persona p WHERE LOWER(p.nombre) like LOWER(:nombre)", Persona.class)
            .setParameter("nombre","%"+ nombre + "%")
            .getResultList();

        return personas.isEmpty() ? Optional.empty() : Optional.of(personas);
    }

    public void deletePersonaByIdentificador(String identificacion){
        entityManager.createQuery(
            "DELETE FROM Persona p WHERE p.identificacion = :identificacion")
            .setParameter("identificacion", identificacion)
            .executeUpdate();
    }
}
