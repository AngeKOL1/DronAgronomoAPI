package com.example.DronAgronomo.service;

import java.math.BigDecimal;

import com.example.DronAgronomo.DTO.TipoEquipoDTO;
import com.example.DronAgronomo.Models.TipoEquipo;

public interface ITipoEquipoService extends IGenericService<TipoEquipo, Integer> {
    TipoEquipoDTO listarEquipoPorRango(BigDecimal min, BigDecimal max);
}