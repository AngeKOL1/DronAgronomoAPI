package com.example.DronAgronomo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DronAgronomo.DTO.LecturasDronDTO;
import com.example.DronAgronomo.Models.LecturasDron;
import com.example.DronAgronomo.service.ILecturaDronService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lecturas-dron")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LecturaDronController {
    
    private final ILecturaDronService lecturaDronService;

    @PostMapping("/lecturas-dron")
    public ResponseEntity<?> registrar(@RequestHeader("X-DRON-KEY") String key, @RequestBody LecturasDronDTO dto) throws Exception {
        if (!key.equals("a82ff93b1be94e4abcb2c6c84dd88f32")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        LecturasDron lectura = lecturaDronService.registrarLecturaDron(dto) ;
        return ResponseEntity.ok(lectura);
    }

}
