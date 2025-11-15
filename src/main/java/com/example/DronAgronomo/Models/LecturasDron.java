package com.example.DronAgronomo.Models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="lecturas_dron")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LecturasDron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idLectura;
    @Column(name = "humedad_aire", precision = 5, scale = 2)
    private BigDecimal humedadSuelo;
    @Column(name = "humedad_aire", precision = 5, scale = 2)
    private BigDecimal humedadAire;
    @Column(name="ph_suelo", precision = 5, scale = 2)
    private BigDecimal phSuelo;
    @Column(name="temperatura_suelo", precision = 5, scale = 2)
    private BigDecimal temperaturaSuelo;
    @Column(name="temperatura_aire", precision = 5, scale = 2)
    private BigDecimal temperaturaAire;

}
