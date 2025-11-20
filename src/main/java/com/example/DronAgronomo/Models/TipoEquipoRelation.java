package com.example.DronAgronomo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "tipo_equipo_relation")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoEquipoRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idTipoEquipoRelation;

    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = false)
    @JsonBackReference(value = "equipo-tipoRelation")
    private Equipos equipo;

    @ManyToOne
    @JoinColumn(name = "tipo_equipo_id", nullable = false)
    @JsonBackReference(value = "tipoEquipo-relation")
    private TipoEquipo tipoEquipo;
}
