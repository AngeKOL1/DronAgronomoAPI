package com.example.DronAgronomo.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.DronAgronomo.DTO.TareaCreateDTO;
import com.example.DronAgronomo.DTO.TareaEstadoDTO;
import com.example.DronAgronomo.Enums.TareasEnum;
import com.example.DronAgronomo.Models.Notificacion;
import com.example.DronAgronomo.Models.Tareas;
import com.example.DronAgronomo.repo.NotificacionRepo;
import com.example.DronAgronomo.repo.TareaRepo;
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
    private final NotificacionRepo notificacionRepo;

    @Override
    protected TareaRepo getRepo() {
        return tareaRepo;
    }

    @Override
    @Transactional
    public Tareas crearTarea(TareaCreateDTO dto) throws Exception {

        // 1. Obtener usuario
        var usuario = usuarioRepo.findById(dto.getUsuarioId())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        // 2. Obtener zona
        var zona = zonasRepo.findById(dto.getZonaId())
                .orElseThrow(() -> new Exception("Zona no encontrada"));

        // 3. Crear tarea
        Tareas tarea = new Tareas();
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFechaInicial(dto.getFechaInicial());
        tarea.setFechaFinal(dto.getFechaFinal());
        tarea.setEstado(dto.getEstado());
        tarea.setUsuario(usuario);
        tarea.setZonas(zona);

        // 4. Guardar tarea
        Tareas tareaGuardada = tareaRepo.save(tarea);

        // 5. Crear notificación automática
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo("Nueva tarea asignada");
        notificacion.setMensaje("Se te ha asignado una nueva tarea: " + tareaGuardada.getDescripcion());
        notificacion.setFechaEnvio(LocalDate.now());
        notificacion.setLeido(false);
        notificacion.setUsuario(usuario);     // asignada al mismo usuario
        notificacion.setTareas(tareaGuardada); // vinculada a la tarea

        // 6. Guardar notificación
        notificacionRepo.save(notificacion);

        // 7. Agregar notificación al set de la tarea (opcional)
        tareaGuardada.getNotificacionesEquipo().add(notificacion);

        return tareaGuardada;
    }
    @Override
    @Transactional
    public Tareas actualizarEstado(Integer idTarea, TareaEstadoDTO dto) throws Exception {

        // 1. Buscar la tarea
        Tareas tarea = tareaRepo.findById(idTarea)
                .orElseThrow(() -> new Exception("La tarea no existe"));

        // 2. Estado anterior
        TareasEnum estadoAnterior = tarea.getEstado();

        // 3. Actualizar estado
        tarea.setEstado(dto.getNuevoEstado());

        // 4. Guardar tarea
        Tareas tareaActualizada = tareaRepo.save(tarea);

        // 5. Crear notificación automática
        Notificacion noti = new Notificacion();
        noti.setTitulo("Actualización de tarea");
        noti.setMensaje(
            "El estado de tu tarea '" + tarea.getDescripcion() +
            "' cambió de " + estadoAnterior + " a " + dto.getNuevoEstado()
        );
        noti.setFechaEnvio(LocalDate.now());
        noti.setLeido(false);
        noti.setUsuario(tarea.getUsuario());
        noti.setTareas(tareaActualizada);

        notificacionRepo.save(noti);

        return tareaActualizada;
    }

}
