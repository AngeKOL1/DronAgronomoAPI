package com.example.DronAgronomo.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.DronAgronomo.DTO.LecturasDronDTO;
import com.example.DronAgronomo.Models.LecturasDron;
import com.example.DronAgronomo.Models.Zonas;
import com.example.DronAgronomo.repo.LecturasDronRepo;
import com.example.DronAgronomo.repo.ZonasRepo;
import com.example.DronAgronomo.service.ILecturaDronService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LecturasDronServiceImpl extends GenericServiceImpl<LecturasDron, Integer> 
        implements ILecturaDronService {

    private final LecturasDronRepo lecturasDronRepo;
    private final ZonasRepo zonasRepo;

    @Override
    protected LecturasDronRepo getRepo() {
        return lecturasDronRepo;
    }

    @Override
    public LecturasDron registrarLecturaDron(LecturasDronDTO dto) {

        Zonas zona = zonasRepo.findById(dto.getZonaId())
                .orElseThrow(() -> new IllegalArgumentException("Zona no encontrada"));

        LecturasDron lectura = new LecturasDron();

        lectura.setHumedadSuelo(
            validarRango(dto.getHumedadSuelo(), 
                          new BigDecimal("0.00"), 
                          new BigDecimal("100.00"))
        );

        lectura.setHumedadAire(
            validarRango(dto.getHumedadAire(), 
                          new BigDecimal("0.00"), 
                          new BigDecimal("100.00"))
        );

        lectura.setPhSuelo(
            validarRango(dto.getPhSuelo(),
                         new BigDecimal("0.00"),
                         new BigDecimal("14.00"))
        );

        lectura.setTemperaturaSuelo(
            validarRango(dto.getTemperaturaSuelo(),
                         new BigDecimal("-10.00"),
                         new BigDecimal("60.00"))
        );

        lectura.setTemperaturaAire(
            validarRango(dto.getTemperaturaAire(),
                         new BigDecimal("-20.00"),
                         new BigDecimal("60.00"))
        );

        lectura.setZona(zona);

        lecturasDronRepo.save(lectura);

        return lectura;
    }

    private BigDecimal validarRango(BigDecimal valor, BigDecimal min, BigDecimal max) {

        if (valor == null) return new BigDecimal("0.00");

        if (valor.compareTo(min) < 0) return new BigDecimal("0.00");

        if (valor.compareTo(max) > 0) return new BigDecimal("0.00");

        return valor;
    }

}
