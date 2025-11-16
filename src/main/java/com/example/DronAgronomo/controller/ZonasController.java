package com.example.DronAgronomo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DronAgronomo.DTO.ZonasDTO;
import com.example.DronAgronomo.Models.Zonas;
import com.example.DronAgronomo.service.IZonasService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/zonas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ZonasController {
    private final IZonasService zonasService;
   @GetMapping
     public ResponseEntity<List<Zonas>> findAll() throws Exception{
        List<Zonas> list = zonasService.findAll();
        return ResponseEntity.ok(list);
    }

     @PostMapping("/registro-zona")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody ZonasDTO dto) throws Exception {
        try {
            Zonas zona = zonasService.registrarZona(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(zona);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }  
}
