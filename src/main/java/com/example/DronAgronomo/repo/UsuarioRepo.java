package com.example.DronAgronomo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DronAgronomo.Models.Usuario;

public interface  UsuarioRepo extends GenericRepo<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u " +
            "LEFT JOIN FETCH u.usuarioRoles ur " +
            "LEFT JOIN FETCH ur.rol " +
            "WHERE u.email = :email")
    Optional<Usuario> findByEmailFetchRoles(@Param("correo") String email);
}
