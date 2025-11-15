package com.example.DronAgronomo.Models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.DronAgronomo.Enums.ZonaEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="zonas")
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Zonas {
    @Id
    @GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idZona;
    @Column(name="nombre", nullable=false, length=100)
    private String nombre;
    @Column(name="descripcion", nullable=false, length=200)
    private String descripcion;
    @Column(name="fecha-at", nullable=false)
    private LocalDate fechaAt;
    @Column(name="latitud", nullable=false, precision=10, scale=7)
    private BigDecimal latitud;
    @Column(name="longitud", nullable=false, precision=10, scale=7)
    private BigDecimal longitud;

    @Enumerated(EnumType.STRING)
    @Column(name="estado", nullable=false, length=20)
    private ZonaEnum estado;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zonas")
    @JsonManagedReference(value="zonas-plantas")
    private Set<Plantas> plantas = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zonas")
    @JsonManagedReference(value="zonas-plantas")
    private Set<LecturasDron> lecturas = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zonas")
    @JsonManagedReference(value="zonas-plantas")
    private Set<Tareas> tareas = new HashSet<>();
}
