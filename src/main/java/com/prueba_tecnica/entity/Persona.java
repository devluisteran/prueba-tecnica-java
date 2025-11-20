package com.prueba_tecnica.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotNull(message = "El nombre es requerido")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    @NotNull(message = "El apellido paterno es requerido")
    @NotBlank(message = "El apellido paterno no puede estar vacío")
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = true)
    private String apellidoMaterno;

    @Column(name = "identificacion", nullable = false, unique = true)
    @NotNull(message = "La identificacion es requerida")
    @NotBlank(message = "La identificacion no puede estar vacía")
    private String identificacion;
}
