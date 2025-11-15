package com.example.DronAgronomo.service;

import com.example.DronAgronomo.DTO.UsuarioDTO;
import com.example.DronAgronomo.Models.Usuario;

public interface IUsuarioService extends IGenericService<Usuario, Integer> {
    Usuario registrarUsuario(UsuarioDTO usuarioDTO) throws Exception;
}
