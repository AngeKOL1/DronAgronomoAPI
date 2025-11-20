package com.example.DronAgronomo.Models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.DronAgronomo.Enums.TareasEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tareas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idTarea;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(name = "fecha_inicial", nullable = false)
    private LocalDate fechaInicial;

    @Column(name = "fecha_final", nullable = false)
    private LocalDate fechaFinal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TareasEnum estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference(value = "usuario-tareas")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "zona_id", nullable = false)
    @JsonBackReference(value = "zona-tareas")
    private Zonas zona;

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "tarea-tareasEquipo")
    private Set<TareasEquipo> tareasEquipo = new HashSet<>();

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value="tarea-notificaciones")
    private Set<Notificacion> notificaciones = new HashSet<>();
}

