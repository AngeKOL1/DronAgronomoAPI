package com.example.DronAgronomo.DTO;

import lombok.Data;

@Data
public class TipoEquipoRelationDTO {
    private Integer idTipoEquipoRelation;
    private EquipoDTO equipo;   // Objeto completo
}
