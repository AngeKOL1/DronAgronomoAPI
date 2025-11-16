package com.example.DronAgronomo.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.DronAgronomo.Enums.ZonaEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZonasDTO {

    private Integer idZona;
    private String nombre;
    private String descripcion;
    private LocalDate fechaAt;

    private BigDecimal latitud;
    private BigDecimal longitud;

    private ZonaEnum estado;
}
