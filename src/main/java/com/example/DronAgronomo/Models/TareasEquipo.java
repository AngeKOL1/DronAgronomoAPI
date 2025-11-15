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
@Table(name="tareas_equipo")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TareasEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idTareaEquipo;
    
    @ManyToOne
    @JoinColumn(name = "tareas_id", nullable = false)
    @JsonBackReference(value = "equipo-tareas")
    private Tareas tareas;

    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = false)
    @JsonBackReference(value = "tareas-equipo")
    private Equipos equipos;

}
