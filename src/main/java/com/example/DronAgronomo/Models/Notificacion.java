package com.example.DronAgronomo.Models;

import java.time.LocalDate;

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
@Table(name="notificaciones")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idNotificacion;
    @Column(name="titulo", nullable=false, length=100)
    private String titulo;
    @Column(name="mensaje", nullable=false, length=200)
    private String mensaje;
    @Column(name="fecha_envio", nullable=false)
    private LocalDate fechaEnvio;
    @Column(name="leido", nullable=false)
    private Boolean leido;
}
