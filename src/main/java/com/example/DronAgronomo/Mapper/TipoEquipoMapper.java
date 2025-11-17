package com.example.DronAgronomo.Mapper;

import java.util.stream.Collectors;

import com.example.DronAgronomo.DTO.EquipoDTO;
import com.example.DronAgronomo.DTO.TipoEquipoDTO;
import com.example.DronAgronomo.DTO.TipoEquipoRelationDTO;
import com.example.DronAgronomo.Models.Equipos;
import com.example.DronAgronomo.Models.TipoEquipo;
import com.example.DronAgronomo.Models.TipoEquipoRelation;
;

public class TipoEquipoMapper {

    public static TipoEquipoDTO toDTO(TipoEquipo entity) {
        if (entity == null) return null;

        TipoEquipoDTO dto = new TipoEquipoDTO();
        dto.setIdTipoEquipo(entity.getIdTipoEquipo());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setMaxTemp(entity.getMaxTemp());
        dto.setMinTemp(entity.getMinTemp());

        if (entity.getRelation() != null) {
            dto.setRelation(
                entity.getRelation()
                      .stream()
                      .map(TipoEquipoMapper::toRelationDTO)
                      .collect(Collectors.toList())
            );
        }

        return dto;
    }

    private static TipoEquipoRelationDTO toRelationDTO(TipoEquipoRelation entity) {
        TipoEquipoRelationDTO dto = new TipoEquipoRelationDTO();
        dto.setIdTipoEquipoRelation(entity.getIdTipoEquipoRelation());
        dto.setEquipo(toEquipoDTO(entity.getEquipos())); // objeto completo
        return dto;
    }

    private static EquipoDTO toEquipoDTO(Equipos entity) {
        if (entity == null) return null;

        EquipoDTO dto = new EquipoDTO();
        dto.setIdEquipo(entity.getIdEquipo());
        dto.setNombreEquipo(entity.getName());
        dto.setModelo(entity.getImg());
        return dto;
    }
}
