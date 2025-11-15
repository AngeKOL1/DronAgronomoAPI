package com.example.DronAgronomo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    @NotNull(message = "La constrasena es obligatoria")
    private String userName;
    @NotNull(message = "El nombre es obligato   rio")
    private String name;
    @NotNull(message = "El apellido es obligatorio")
    private String lastName;
    @NotNull(message = "El email es obligatorio")
    private String email;
    @NotNull(message = "La constraseña es obligatoria")
    private String password;
    @NotNull(message = "La confirmación de constraseña es obligatoria")
    private String confirmPassword;  
}
