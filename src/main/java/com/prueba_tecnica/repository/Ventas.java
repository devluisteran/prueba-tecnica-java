package com.prueba_tecnica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.prueba_tecnica.entity.Factura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class Ventas {

    @PersistenceContext
    private EntityManager entityManager;

    public Factura storeFactura(Factura factura){
        entityManager.persist(factura);
        return factura;
    }

    public Optional<List<Factura>> findFacturasByPersona(Long personaId){

        List<Factura> facturas = entityManager.createQuery(
            "SELECT f FROM Factura f WHERE f.persona.id = :personaId", Factura.class)
            .setParameter("personaId", personaId)
            .getResultList();

        return facturas.isEmpty() ? Optional.empty() : Optional.of(facturas);
    }

}
