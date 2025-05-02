package com.julia.taskmanagerapi.mapper;

import com.julia.taskmanagerapi.dto.TaskRequestDTO;
import com.julia.taskmanagerapi.dto.TaskResponseDTO;
import com.julia.taskmanagerapi.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Tarefa toEntity(TaskRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrazo(dto.getPrazo());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setStatus(dto.getStatus());
        return tarefa;
    }

    public TaskResponseDTO toDTO(Tarefa tarefa) {
        return new TaskResponseDTO(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getDescricao(),
            tarefa.getPrazo(),
            tarefa.getPrioridade(),
            tarefa.getStatus()
        );
    }
}
