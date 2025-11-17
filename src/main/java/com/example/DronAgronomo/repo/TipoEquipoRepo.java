package com.example.DronAgronomo.repo;

import java.util.Optional;

import com.example.DronAgronomo.Models.TipoEquipo;

public interface  TipoEquipoRepo extends GenericRepo<TipoEquipo, Integer>{
    Optional<TipoEquipo> findByNombre(String nombre);

}
