package com.example.DronAgronomo.service;

import com.example.DronAgronomo.DTO.ZonasDTO;
import com.example.DronAgronomo.Models.Zonas;

public interface  IZonasService extends IGenericService<Zonas, Integer>{
    Zonas registrarZona(ZonasDTO zonaDTO) throws Exception;
}
