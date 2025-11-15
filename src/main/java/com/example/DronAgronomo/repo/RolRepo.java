package com.example.DronAgronomo.repo;

import java.util.Optional;

import com.example.DronAgronomo.Models.Rol;

public interface RolRepo extends GenericRepo<Rol, Integer> {
    Optional <Rol> findByName(String nombre);
}
