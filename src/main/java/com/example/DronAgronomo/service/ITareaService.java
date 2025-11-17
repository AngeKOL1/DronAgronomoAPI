package com.example.DronAgronomo.service;

import com.example.DronAgronomo.DTO.TareaCreateDTO;
import com.example.DronAgronomo.DTO.TareaEstadoDTO;
import com.example.DronAgronomo.Models.Tareas;

public interface  ITareaService extends IGenericService<Tareas, Integer>{
   Tareas crearTarea(TareaCreateDTO idUser) throws Exception; 
   Tareas actualizarEstado(Integer idTarea, TareaEstadoDTO dto) throws Exception;
}
