package com.example.DronAgronomo.DTO;

import java.time.LocalDate;

import com.example.DronAgronomo.Enums.TareasEnum;

import lombok.Data;


@Data
public class TareaCreateDTO {
    private String descripcion;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private TareasEnum estado;

    private Integer usuarioId;
    private Integer zonaId;   // (equipo_id en tu entidad)
}
