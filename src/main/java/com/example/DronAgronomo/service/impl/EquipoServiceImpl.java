package com.example.DronAgronomo.service.impl;

import org.springframework.stereotype.Service;

import com.example.DronAgronomo.Models.Equipos;
import com.example.DronAgronomo.repo.EquipoRepo;
import com.example.DronAgronomo.repo.TipoEquipoRepo;
import com.example.DronAgronomo.service.IEquipoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl extends GenericServiceImpl<Equipos, Integer> implements IEquipoService {
    private final EquipoRepo equipoRepo;
    private final TipoEquipoRepo tipoEquipoRepo;
    @Override
    protected EquipoRepo getRepo() {
        return equipoRepo;
    }
}
