package com.example.DronAgronomo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DronAgronomo.DTO.LecturaRespuestaDTO;
import com.example.DronAgronomo.DTO.ZonaResumenDTO;
import com.example.DronAgronomo.DTO.ZonasDTO;
import com.example.DronAgronomo.Models.Zonas;
import com.example.DronAgronomo.repo.ZonasRepo;
import com.example.DronAgronomo.service.IZonasService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZonaServiceImpl extends GenericServiceImpl<Zonas, Integer> implements IZonasService {

    private final ZonasRepo zonasRepo;

    @Override
    protected ZonasRepo getRepo() {
        return zonasRepo;
    }

    @Override
    public Zonas registrarZona(ZonasDTO dto) throws Exception {

        if (dto == null) throw new IllegalArgumentException("El objeto ZonasDTO no puede ser nulo.");

        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty())
            throw new IllegalArgumentException("El nombre de la zona es obligatorio.");

        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty())
            throw new IllegalArgumentException("La descripción es obligatoria.");

        if (dto.getFechaAt() == null)
            throw new IllegalArgumentException("La fecha es obligatoria.");

        if (dto.getLatitud() == null)
            throw new IllegalArgumentException("La latitud es obligatoria.");

        if (dto.getLongitud() == null)
            throw new IllegalArgumentException("La longitud es obligatoria.");

        if (dto.getLatitud().doubleValue() < -90 || dto.getLatitud().doubleValue() > 90)
            throw new IllegalArgumentException("La latitud debe estar entre -90 y 90.");

        if (dto.getLongitud().doubleValue() < -180 || dto.getLongitud().doubleValue() > 180)
            throw new IllegalArgumentException("La longitud debe estar entre -180 y 180.");

        if (dto.getEstado() == null)
            throw new IllegalArgumentException("El estado es obligatorio.");

        Zonas zona = new Zonas();
        zona.setNombre(dto.getNombre().trim());
        zona.setDescripcion(dto.getDescripcion().trim());
        zona.setFechaAt(dto.getFechaAt());
        zona.setLatitud(dto.getLatitud());
        zona.setLongitud(dto.getLongitud());
        zona.setEstado(dto.getEstado());

        zona.setPlantas(new java.util.HashSet<>());
        zona.setLecturas(new java.util.HashSet<>());
        zona.setTareas(new java.util.HashSet<>());

        zonasRepo.save(zona);

        return zona;
    }

    @Override
    public List<ZonaResumenDTO> findAllDTO() {

        return zonasRepo.findAll()
                .stream()
                .map(z -> new ZonaResumenDTO(
                        z.getIdZona().longValue(),
                        z.getNombre(),
                        z.getDescripcion(),
                        z.getFechaAt().toString(),
                        z.getLatitud().doubleValue(),
                        z.getLongitud().doubleValue(),
                        z.getEstado().name()
                ))
                .toList();
    }

    @Override
    public List<LecturaRespuestaDTO> obtenerPorZona(Integer idZona) {

        Zonas zona = zonasRepo.findById(idZona)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada"));

        // CORRECTO: getLecturas()
        return zona.getLecturas()
                .stream()
                .map(l -> new LecturaRespuestaDTO(
                        l.getIdLectura(),
                        l.getHumedadSuelo(),
                        l.getHumedadAire(),
                        l.getPhSuelo(),
                        l.getTemperaturaSuelo(),
                        l.getTemperaturaAire()
                ))
                .toList();
    }
}
