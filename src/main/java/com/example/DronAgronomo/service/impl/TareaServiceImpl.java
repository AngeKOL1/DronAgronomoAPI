package com.example.DronAgronomo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DronAgronomo.DTO.TareaCreateDTO;
import com.example.DronAgronomo.DTO.TareaEstadoDTO;
import com.example.DronAgronomo.DTO.TareaEstadoMovilDTO;
import com.example.DronAgronomo.DTO.TareaRequest;
import com.example.DronAgronomo.Enums.TareasEnum;
import com.example.DronAgronomo.Models.Equipos;
import com.example.DronAgronomo.Models.Notificacion;
import com.example.DronAgronomo.Models.Tareas;
import com.example.DronAgronomo.Models.TareasEquipo;
import com.example.DronAgronomo.Models.Usuario;
import com.example.DronAgronomo.Models.Zonas;
import com.example.DronAgronomo.repo.EquipoRepo;
import com.example.DronAgronomo.repo.NotificacionRepo;
import com.example.DronAgronomo.repo.TareaRepo;
import com.example.DronAgronomo.repo.TareasEquipoRepo;
import com.example.DronAgronomo.repo.TipoEquipoRepo;
import com.example.DronAgronomo.repo.UsuarioRepo;
import com.example.DronAgronomo.repo.ZonasRepo;
import com.example.DronAgronomo.service.ITareaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl extends GenericServiceImpl<Tareas, Integer> implements ITareaService {

    private final TareaRepo tareaRepo;
    private final UsuarioRepo usuarioRepo;
    private final ZonasRepo zonasRepo;
    private final EquipoRepo equiposRepo;         // ← CORRECTO
    private final TareasEquipoRepo tareasEquipoRepo;
    private final NotificacionRepo notificacionRepo;

    @Override
    protected TareaRepo getRepo() {
        return tareaRepo;
    }

    @Override
    @Transactional
    public Tareas crearTarea(TareaRequest dto) {

        Usuario usuario = usuarioRepo.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Zonas zona = zonasRepo.findById(dto.getZonaId())
            .orElseThrow(() -> new RuntimeException("Zona no encontrada"));

        Equipos equipo = equiposRepo.findById(dto.getEquipoId())   // ← CORRECTO
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Tareas tarea = new Tareas();
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFechaInicial(dto.getFechaInicial());
        tarea.setFechaFinal(dto.getFechaFinal());
        tarea.setEstado(dto.getEstado());
        tarea.setUsuario(usuario);
        tarea.setZona(zona);

        Tareas saved = tareaRepo.save(tarea);

        TareasEquipo rel = new TareasEquipo();
        rel.setTarea(saved);
        rel.setEquipo(equipo);
        tareasEquipoRepo.save(rel);

        // notificación
        Notificacion n = new Notificacion();
        n.setTitulo("Nueva tarea creada");
        n.setMensaje("Se ha creado la tarea: " + saved.getDescripcion());
        n.setFechaEnvio(LocalDate.now());
        n.setLeido(false);
        n.setUsuario(usuario);
        n.setTarea(saved);
        notificacionRepo.save(n);

        saved.getNotificaciones().add(n);

        return saved;
    }
    @Override
    @Transactional
    public Tareas actualizarEstado(Integer idTarea, TareaEstadoDTO dto) throws Exception {

        // 1. Buscar tarea
        Tareas tarea = tareaRepo.findById(idTarea)
                .orElseThrow(() -> new Exception("La tarea no existe"));

        // 2. Guardar estado anterior
        TareasEnum estadoAnterior = tarea.getEstado();

        // 3. Actualizar estado
        tarea.setEstado(dto.getNuevoEstado());

        // 4. Guardar
        Tareas actualizada = tareaRepo.save(tarea);

        // 5. Crear notificación automática
        Notificacion noti = new Notificacion();
        noti.setTitulo("Actualización de tarea");
        noti.setMensaje(
            "El estado de la tarea '" + tarea.getDescripcion() + 
            "' cambió de " + estadoAnterior + " a " + dto.getNuevoEstado()
        );
        noti.setFechaEnvio(LocalDate.now());
        noti.setLeido(false);
        noti.setUsuario(tarea.getUsuario());
        noti.setTarea(actualizada);

        notificacionRepo.save(noti);

        return actualizada;
    }

    @Override
    public List<Tareas> obtenerTareasPorUsuario(Integer idUsuario) {
        return tareaRepo.findByUsuario(idUsuario);
    }

    @Override
    @Transactional
    public Tareas actualizarEstadoMovil(Integer idTarea, TareaEstadoMovilDTO dto) throws Exception {


        Tareas tarea = tareaRepo.findById(idTarea)
            .orElseThrow(() -> new Exception("La tarea no existe"));

        if (!tarea.getUsuario().getIdUser().equals(dto.getUsuarioId())) {
            throw new Exception("No tienes permiso para actualizar esta tarea");
        }

        TareasEnum estadoAnterior = tarea.getEstado();

        tarea.setEstado(dto.getNuevoEstado());
        
        Tareas tareaActualizada = tareaRepo.save(tarea);

        Notificacion noti = new Notificacion();
        noti.setTitulo("Tarea actualizada por el trabajador");
        noti.setMensaje(
            "Tu tarea '" + tarea.getDescripcion() + 
            "' cambió de " + estadoAnterior + " a " + dto.getNuevoEstado()
        );
        noti.setFechaEnvio(LocalDate.now());
        noti.setLeido(false);
        noti.setUsuario(tarea.getUsuario());
        noti.setTarea(tareaActualizada);

        notificacionRepo.save(noti);

        return tareaActualizada;
    }



}
