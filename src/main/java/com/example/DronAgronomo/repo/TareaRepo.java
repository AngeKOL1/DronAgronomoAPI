package com.example.DronAgronomo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.DronAgronomo.Models.Tareas;

public interface TareaRepo extends GenericRepo<Tareas, Integer>{
    @Query("SELECT t FROM Tareas t WHERE t.usuario.idUser = :idUsuario")
    List<Tareas> findByUsuario(Integer idUsuario);   
}
