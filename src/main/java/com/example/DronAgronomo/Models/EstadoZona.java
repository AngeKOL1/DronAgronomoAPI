package com.example.DronAgronomo.Models;

import java.time.LocalDate;

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
@Table(name="estado_zona")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstadoZona {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEstadoZona;
    @Column(name="motivo", nullable=false, length=200)
    private String motivo;
    @Column(name="date_at", nullable=false)
    private LocalDate dateAt;
    @Column(name="estado", nullable=false, length=50)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference(value = "usuario-estadoZonas")
    private Usuario usuario;
}
