package com.example.DronAgronomo.service;

import java.util.List;

import com.example.DronAgronomo.DTO.LecturaRespuestaDTO;
import com.example.DronAgronomo.DTO.ZonaResumenDTO;
import com.example.DronAgronomo.DTO.ZonasDTO;
import com.example.DronAgronomo.Models.Zonas;

public interface IZonasService extends IGenericService<Zonas, Integer> {

    Zonas registrarZona(ZonasDTO zonaDTO) throws Exception;

    List<ZonaResumenDTO> findAllDTO();

    List<LecturaRespuestaDTO> obtenerPorZona(Integer idZona);
}
