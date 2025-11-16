package com.example.DronAgronomo.service;

import com.example.DronAgronomo.DTO.LecturasDronDTO;
import com.example.DronAgronomo.Models.LecturasDron;

public interface ILecturaDronService extends IGenericService<LecturasDron, Integer> {
    LecturasDron registrarLecturaDron(LecturasDronDTO lecturaDronDTO) throws Exception;
}
