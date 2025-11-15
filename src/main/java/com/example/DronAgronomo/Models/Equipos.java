package com.example.DronAgronomo.Models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="equipos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Equipos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEquipo;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="description", nullable=false)
    private String description;
    @Column(name="img", nullable=false)
    private String img;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "equipos")
    @JsonManagedReference(value = "usuario-equipo")
    private Set<TareasEquipo> tareasEquipo = new HashSet<>();
}
