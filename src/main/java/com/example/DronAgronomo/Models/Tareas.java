package com.example.DronAgronomo.Models;

import java.time.LocalDate;

import com.example.DronAgronomo.Enums.TareasEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tareas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tareas {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) 
    @EqualsAndHashCode.Include
    private Integer idTarea;
    @Column(name="descripcion", nullable=false, length=200)
    private String descripcion;
    @Column(name="fecha_inicial", nullable=false)
    private LocalDate fechaInicial;
    @Column(name="fecha_final", nullable=false)
    private LocalDate fechaFinal;
    @Enumerated(EnumType.STRING)
    @Column(name="estado", nullable=false, length=20)
    private TareasEnum estado;
}
