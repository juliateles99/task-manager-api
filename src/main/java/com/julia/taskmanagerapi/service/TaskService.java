package com.julia.taskmanagerapi.service;

import com.julia.taskmanagerapi.dto.TaskRequestDTO;
import com.julia.taskmanagerapi.dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    TaskResponseDTO create(TaskRequestDTO dto);
    TaskResponseDTO update(Long id, TaskRequestDTO dto);
    TaskResponseDTO getById(Long id);
    List<TaskResponseDTO> getAll();
    void delete(Long id);
}