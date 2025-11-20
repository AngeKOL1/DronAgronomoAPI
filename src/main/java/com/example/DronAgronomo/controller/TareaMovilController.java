package com.example.DronAgronomo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.DronAgronomo.DTO.TareaEstadoMovilDTO;
import com.example.DronAgronomo.Models.Tareas;
import com.example.DronAgronomo.service.ITareaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mobile/tareas")
@RequiredArgsConstructor
public class TareaMovilController {

    private final ITareaService tareaService;
    @GetMapping("/{usuarioId}")
    public List<Tareas> listarPorUsuario(@PathVariable Integer usuarioId) {
        return tareaService.obtenerTareasPorUsuario(usuarioId);
    }

    @PutMapping("/{idTarea}/estado")
    public Tareas actualizarEstado(
            @PathVariable Integer idTarea,
            @RequestBody TareaEstadoMovilDTO dto) throws Exception {

        return tareaService.actualizarEstadoMovil(idTarea, dto);
    }
}
