package com.example.DronAgronomo.service;

import java.util.List;

import com.example.DronAgronomo.DTO.TareaCreateDTO;
import com.example.DronAgronomo.DTO.TareaEstadoDTO;
import com.example.DronAgronomo.DTO.TareaEstadoMovilDTO;
import com.example.DronAgronomo.DTO.TareaRequest;
import com.example.DronAgronomo.Models.Tareas;

public interface  ITareaService extends IGenericService<Tareas, Integer>{
   Tareas crearTarea(TareaRequest dto) throws Exception;

   Tareas actualizarEstado(Integer idTarea, TareaEstadoDTO dto) throws Exception;
   List<Tareas> obtenerTareasPorUsuario(Integer idUsuario);
   Tareas actualizarEstadoMovil(Integer idTarea, TareaEstadoMovilDTO dto) throws Exception;


}
