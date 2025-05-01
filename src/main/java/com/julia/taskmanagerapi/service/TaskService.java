package com.julia.taskmanagerapi.service;

import com.julia.taskmanagerapi.dto.TaskRequestDTO;
import com.julia.taskmanagerapi.dto.TaskResponseDTO;
import com.julia.taskmanagerapi.model.Prioridade;
import com.julia.taskmanagerapi.model.Status;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    TaskResponseDTO create(TaskRequestDTO dto);
    TaskResponseDTO update(Long id, TaskRequestDTO dto);
    TaskResponseDTO getById(Long id);
    List<TaskResponseDTO> getAll();
    void delete(Long id);

    List<TaskResponseDTO> findByStatus(Status status);
    List<TaskResponseDTO> findByPrioridade(Prioridade prioridade);
    List<TaskResponseDTO> findByPrazoBetween(LocalDate inicio, LocalDate fim);
}
