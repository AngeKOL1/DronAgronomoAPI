package com.example.DronAgronomo.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name="zonas")
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Zonas {
    @Id
    @GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idZona;
    @Column(name="nombre", nullable=false, length=100)
    private String nombre;
    @Column(name="nombre", nullable=false, length=200)
    private String descripcion;
    @Column(name="nombre", nullable=false)
    private LocalDate fechaAt;
    @Column(name="latitud", nullable=false, precision=10, scale=7)
    private BigDecimal latitud;
    @Column(name="latitud", nullable=false, precision=10, scale=7)
    private BigDecimal longitud;
    @Enumerated(EnumType.STRING)
    @Column(name="estado", nullable=false, length=20)
    private EstadoZona estado;
}
