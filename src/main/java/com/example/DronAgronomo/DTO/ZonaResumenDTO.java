package com.example.DronAgronomo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZonaResumenDTO {
    private Long idZona;
    private String nombre;
    private String descripcion;
    private String fechaAt;
    private Double latitud;
    private Double longitud;
    private String estado;
}
