package com.example.DronAgronomo.DTO;

import java.time.LocalDate;

import com.example.DronAgronomo.Enums.TareasEnum;

import lombok.Data;

@Data
public class TareaRequest {

    private String descripcion;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private TareasEnum estado;

    private Integer usuarioId;
    private Integer zonaId;

    private Integer equipoId; // <-- ESTE ES NUEVO
}
