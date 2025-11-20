package com.prueba_tecnica.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "fecha", nullable = false)
    @NotNull(message = "La fecha es requerida")
    private Date fecha;

    @Column(name = "total", nullable = false)
    @NotNull(message = "El total es requerido")
    @PositiveOrZero(message = "El total debe ser mayor o igual a cero")
    private Double total;

    @NotNull(message = "La persona es obligatoria")
    @ManyToOne
    @JoinColumn(name="persona_id", referencedColumnName = "persona_id")
    private Persona persona;


}
