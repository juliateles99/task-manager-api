package com.julia.taskmanagerapi.service;

import com.julia.taskmanagerapi.dto.TaskRequestDTO;
import com.julia.taskmanagerapi.dto.TaskResponseDTO;
import com.julia.taskmanagerapi.model.Prioridade;
import com.julia.taskmanagerapi.model.Status;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(Long id);
    TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO);
    void deleteTask(Long id);

    List<TaskResponseDTO> findByStatus(Status status);
    List<TaskResponseDTO> findByPrioridade(Prioridade prioridade);
    List<TaskResponseDTO> findByPrazoBetween(LocalDate inicio, LocalDate fim);
}
