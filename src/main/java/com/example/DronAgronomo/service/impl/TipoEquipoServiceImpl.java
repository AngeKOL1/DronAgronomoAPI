package com.example.DronAgronomo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DronAgronomo.DTO.TipoEquipoDTO;
import com.example.DronAgronomo.Mapper.TipoEquipoMapper;
import com.example.DronAgronomo.Models.TipoEquipo;
import com.example.DronAgronomo.repo.TipoEquipoRepo;
import com.example.DronAgronomo.service.ITipoEquipoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoEquipoServiceImpl extends GenericServiceImpl<TipoEquipo, Integer> implements ITipoEquipoService {
    private final TipoEquipoRepo tipoEquipoRepo;

    @Override
    protected TipoEquipoRepo getRepo() {
        return tipoEquipoRepo;
    }

    @Override
    public TipoEquipoDTO listarEquipoPorRango(BigDecimal min, BigDecimal max) {
        if (min == null || max == null) {
            throw new IllegalArgumentException("Los parámetros min y max son obligatorios.");
        }
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("El parámetro min no puede ser mayor que max.");
        }

        List<TipoEquipo> equipos = tipoEquipoRepo.findAll();
        if (equipos == null || equipos.isEmpty()) {
            throw new IllegalArgumentException("No hay tipos de equipo registrados.");
        }

        TipoEquipo mejor = null;
        BigDecimal mejorDist = null;

        for (TipoEquipo t : equipos) {
            if (t.getMinTemp() == null || t.getMaxTemp() == null) continue;

            BigDecimal dist = BigDecimal.ZERO;

            if (min.compareTo(t.getMinTemp()) < 0) {
                dist = dist.add(t.getMinTemp().subtract(min));
            }

            if (max.compareTo(t.getMaxTemp()) > 0) {
                dist = dist.add(max.subtract(t.getMaxTemp()));
            }

            if (mejor == null || mejorDist == null || dist.compareTo(mejorDist) < 0) {
                mejor = t;
                mejorDist = dist;

                if (mejorDist.compareTo(BigDecimal.ZERO) == 0) break;
            }
        }

        if (mejor == null) {
            throw new IllegalArgumentException("No se encontró un tipo de equipo adecuado para el rango.");
        }

        return TipoEquipoMapper.toDTO(mejor);
    }

}
