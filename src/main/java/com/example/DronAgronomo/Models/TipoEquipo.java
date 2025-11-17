package com.example.DronAgronomo.Models;

import java.math.BigDecimal;
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
@Table(name = "tipo_equipo")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idTipoEquipo;
    @Column(name ="nombre", nullable = false, unique = true, length = 50)
    private String nombre; 
    @Column(name ="descripcion", nullable = false, unique = true, length = 200)
    private String descripcion;
    @Column(name ="max-temp", nullable = false)
    private BigDecimal maxTemp;
    @Column(name ="min-temp", nullable = false)
    private BigDecimal minTemp;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tipoEquipo")
    @JsonManagedReference(value = "tipoEquipo")
    private Set<TipoEquipoRelation> relation = new HashSet<>();
}
