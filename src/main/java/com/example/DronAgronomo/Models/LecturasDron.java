package com.example.DronAgronomo.Models;

import java.math.BigDecimal;

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
@Table(name="lecturas_dron")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LecturasDron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idLectura;

    @Column(precision = 5, scale = 2)
    private BigDecimal humedadSuelo;

    @Column(precision = 5, scale = 2)
    private BigDecimal humedadAire;

    @Column(precision = 5, scale = 2)
    private BigDecimal phSuelo;

    @Column(precision = 5, scale = 2)
    private BigDecimal temperaturaSuelo;

    @Column(precision = 5, scale = 2)
    private BigDecimal temperaturaAire;

    @ManyToOne
    @JoinColumn(name = "zona_id", nullable = false)
    @JsonBackReference(value = "zona-lecturas")
    private Zonas zona;
}
