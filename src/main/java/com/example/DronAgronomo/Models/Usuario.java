package com.example.DronAgronomo.Models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idUser;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name ="email", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name ="password", nullable = false)
    private String password;
    @Column(name ="name", nullable = false)
    private String name;
    @Column(name ="last_name", nullable = false)
    private String lastName;
    @Column(name ="date_at", nullable = false)
    private LocalDate dateAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonManagedReference(value = "usuario-roles")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonManagedReference(value = "usuario-zonas")
    private Set<EstadoZona> estadoZonas= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonManagedReference(value="usuario-notificaciones")
    private Set<Notificacion> notificacions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonManagedReference(value="usuario-notificaciones")
    private Set<Tareas> tareas = new HashSet<>();
}
