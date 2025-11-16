package com.example.DronAgronomo.service.impl;

import org.springframework.stereotype.Service;

import com.example.DronAgronomo.DTO.ZonasDTO;
import com.example.DronAgronomo.Models.Zonas;
import com.example.DronAgronomo.repo.ZonasRepo;
import com.example.DronAgronomo.service.IZonasService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ZonaServiceImpl extends GenericServiceImpl<Zonas, Integer> implements IZonasService{
    private final ZonasRepo zonasRepo;
    @Override
    protected ZonasRepo getRepo() {
        return zonasRepo;
    }
    @Override
    public Zonas registrarZona(ZonasDTO dto) throws Exception {

        if (dto == null) {
            throw new IllegalArgumentException("El objeto ZonasDTO no puede ser nulo.");
        }

        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la zona es obligatorio.");
        }

        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria.");
        }

        if (dto.getFechaAt() == null) {
            throw new IllegalArgumentException("La fecha de actualización es obligatoria.");
        }

        if (dto.getLatitud() == null) {
            throw new IllegalArgumentException("La latitud es obligatoria.");
        }

        if (dto.getLongitud() == null) {
            throw new IllegalArgumentException("La longitud es obligatoria.");
        }

        // Validación de rango de latitud (-90 a 90)
        if (dto.getLatitud().doubleValue() < -90 || dto.getLatitud().doubleValue() > 90) {
            throw new IllegalArgumentException("La latitud debe estar entre -90 y 90 grados.");
        }

        // Validación de rango para longitud (-180 a 180)
        if (dto.getLongitud().doubleValue() < -180 || dto.getLongitud().doubleValue() > 180) {
            throw new IllegalArgumentException("La longitud debe estar entre -180 y 180 grados.");
        }

        if (dto.getEstado() == null) {
            throw new IllegalArgumentException("El estado de la zona es obligatorio.");
        }


        Zonas zona = new Zonas();
        zona.setIdZona(null); // Garantizamos creación nueva
        zona.setNombre(dto.getNombre().trim());
        zona.setDescripcion(dto.getDescripcion().trim());
        zona.setFechaAt(dto.getFechaAt());
        zona.setLatitud(dto.getLatitud());
        zona.setLongitud(dto.getLongitud());
        zona.setEstado(dto.getEstado());


        zona.setPlantas(new java.util.HashSet<>());
        zona.setLecturas(new java.util.HashSet<>());
        zona.setTareas(new java.util.HashSet<>());

        // ===== GUARDAR =====

        zonasRepo.save(zona);

        return zona;
    }

}
