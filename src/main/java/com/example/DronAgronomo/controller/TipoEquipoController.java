package com.example.DronAgronomo.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DronAgronomo.DTO.TipoEquipoDTO;
import com.example.DronAgronomo.service.ITipoEquipoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipo-equipos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TipoEquipoController {
    private final ITipoEquipoService tipoEquipoService;
    
    @GetMapping("/buscar-por-rango-temperatura")
    public ResponseEntity<?> buscarPorRangoTemperatura(@RequestParam BigDecimal min,
                                                    @RequestParam BigDecimal max) {
        try {
            TipoEquipoDTO resultado = tipoEquipoService.listarEquipoPorRango(min, max);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }



}
