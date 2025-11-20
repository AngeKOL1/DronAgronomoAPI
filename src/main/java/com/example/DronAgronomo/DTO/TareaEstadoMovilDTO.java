package com.example.DronAgronomo.DTO;

import com.example.DronAgronomo.Enums.TareasEnum;
import lombok.Data;

@Data
public class TareaEstadoMovilDTO {
    private Integer usuarioId;
    private TareasEnum nuevoEstado;
}
