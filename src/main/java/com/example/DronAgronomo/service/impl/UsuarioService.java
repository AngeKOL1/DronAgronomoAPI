package com.example.DronAgronomo.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DronAgronomo.DTO.UsuarioDTO;
import com.example.DronAgronomo.Models.Rol;
import com.example.DronAgronomo.Models.Usuario;
import com.example.DronAgronomo.Models.UsuarioRol;
import com.example.DronAgronomo.repo.RolRepo;
import com.example.DronAgronomo.repo.UsuarioRepo;
import com.example.DronAgronomo.service.IUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericServiceImpl<Usuario, Integer> implements IUsuarioService {

    private final UsuarioRepo usuarioRepo;
    private final RolRepo rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected com.example.DronAgronomo.repo.UsuarioRepo getRepo() {
        return usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {

         if (!usuarioDTO.getPassword().equals(usuarioDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        if (usuarioRepo.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();

        usuario.setName(usuarioDTO.getName());
        usuario.setLastName(usuarioDTO.getLastName());
        usuario.setUserName(usuarioDTO.getUserName());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setDateAt(LocalDate.now());

        String passwordEncriptada = passwordEncoder.encode(usuarioDTO.getPassword());
        usuario.setPassword(passwordEncriptada);

        usuario.setUsuarioRoles(new HashSet<>());
        usuario.setEstadoZonas(new HashSet<>());
        usuario.setNotificacions(new HashSet<>());
        usuario.setTareas(new HashSet<>());

        return save(usuario);
    }

    @Override
    public Usuario save(Usuario usuario){
        if (usuario.getUsuarioRoles() == null) usuario.setUsuarioRoles(new HashSet<>());
        if (usuario.getEstadoZonas() == null) usuario.setEstadoZonas(new HashSet<>());
        if (usuario.getNotificacions() == null) usuario.setNotificacions(new HashSet<>());
        if (usuario.getTareas() == null) usuario.setTareas(new HashSet<>());
        Rol rolUsuario = rolRepo.findByName("TRABAJADOR")
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rolUsuario);
        usuarioRol.setFechaAsignacion(LocalDate.now());
        usuarioRol.setActivo(true);

        usuario.getUsuarioRoles().add(usuarioRol);
        
        return usuarioRepo.save(usuario);
    }

     @Override
    public List<Usuario> listarTrabajadores() {
        return usuarioRepo.findByRolId(2); // Rol TRABAJADOR
    }
}
