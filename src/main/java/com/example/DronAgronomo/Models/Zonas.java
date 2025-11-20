package com.example.DronAgronomo.Models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.DronAgronomo.Enums.ZonaEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zonas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Zonas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idZona;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(name = "fecha_at", nullable = false)
    private LocalDate fechaAt;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal latitud;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal longitud;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ZonaEnum estado;

    // 🌱 Plantas (mappedBy = "zona")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zona")
    @JsonManagedReference(value = "zona-plantas")
    private Set<Plantas> plantas = new HashSet<>();

    // 📡 Lecturas (mappedBy = "zona")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zona")
    @JsonManagedReference(value = "zona-lecturas")
    private Set<LecturasDron> lecturas = new HashSet<>();

    // 📝 Tareas (mappedBy = "zona")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zona")
    @JsonManagedReference(value = "zona-tareas")
    private Set<Tareas> tareas = new HashSet<>();
}
