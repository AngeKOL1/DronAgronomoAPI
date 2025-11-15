package com.example.DronAgronomo.Models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="plantas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Plantas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPlanta;
    @Column(name="nombre_comun", nullable=false)
    private String nombreComun;
    @Column(name="nombre_cientifico", nullable=false)
    private String nombreCientifico;
    @Column(name="latitud", precision = 10, scale = 7)
    private BigDecimal latitud;
    @Column(name="longitud", precision = 10, scale = 7)
    private BigDecimal longitud;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference(value = "zonas-plantas")
    private Zonas zonas;
}