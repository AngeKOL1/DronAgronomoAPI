package com.example.DronAgronomo.service.impl;

import org.springframework.stereotype.Service;

import com.example.DronAgronomo.Models.Usuario;
import com.example.DronAgronomo.repo.UsuarioRepo;
import com.example.DronAgronomo.service.IUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericServiceImpl<Usuario, Integer> implements IUsuarioService {

    private final UsuarioRepo repo;

    @Override
    protected com.example.DronAgronomo.repo.UsuarioRepo getRepo() {
        return repo;
    }

}
