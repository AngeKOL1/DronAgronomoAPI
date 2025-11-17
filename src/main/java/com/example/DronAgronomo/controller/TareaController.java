package com.example.DronAgronomo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DronAgronomo.DTO.TareaCreateDTO;
import com.example.DronAgronomo.DTO.TareaEstadoDTO;
import com.example.DronAgronomo.Models.Tareas;
import com.example.DronAgronomo.service.ITareaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tareas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TareaController {
    private final ITareaService tareaService;
    @PostMapping("/crear")
    public ResponseEntity<?> crearTarea(@RequestBody TareaCreateDTO dto) throws Exception {
        var tarea = tareaService.crearTarea(dto);
        return ResponseEntity.ok(tarea);
    }
    @PutMapping("/{id}/estado")
    public ResponseEntity<Tareas> actualizarEstado(
            @PathVariable Integer id,
            @RequestBody TareaEstadoDTO dto) throws Exception {

        return ResponseEntity.ok(tareaService.actualizarEstado(id, dto));
    }


}
