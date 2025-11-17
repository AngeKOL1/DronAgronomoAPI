package com.example.DronAgronomo.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class TipoEquipoDTO {
    private Integer idTipoEquipo;
    private String nombre;
    private String descripcion;
    private BigDecimal maxTemp;
    private BigDecimal minTemp;
    private List<TipoEquipoRelationDTO> relation;
}
