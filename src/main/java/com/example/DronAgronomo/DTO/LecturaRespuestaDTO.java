package com.example.DronAgronomo.DTO;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturaRespuestaDTO {

    private Integer idLectura;
    private BigDecimal humedadSuelo;
    private BigDecimal humedadAire;
    private BigDecimal phSuelo;
    private BigDecimal temperaturaSuelo;
    private BigDecimal temperaturaAire;
}
