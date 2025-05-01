package com.julia.taskmanagerapi.service.impl;

import com.julia.taskmanagerapi.dto.TaskRequestDTO;
import com.julia.taskmanagerapi.dto.TaskResponseDTO;
import com.julia.taskmanagerapi.exception.TaskNotFoundException;
import com.julia.taskmanagerapi.mapper.TaskMapper;
import com.julia.taskmanagerapi.model.Tarefa;
import com.julia.taskmanagerapi.repository.TaskRepository;
import com.julia.taskmanagerapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Override
    public TaskResponseDTO create(TaskRequestDTO dto) {
        Tarefa tarefa = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(tarefa));
    }

    @Override
    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada com id: " + id));
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setPrazo(dto.prazo());
        tarefa.setPrioridade(dto.prioridade());
        tarefa.setStatus(dto.status());
        return mapper.toDTO(repository.save(tarefa));
    }

    @Override
    public TaskResponseDTO getById(Long id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada com id: " + id));
        return mapper.toDTO(tarefa);
    }

    @Override
    public List<TaskResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException("Tarefa não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}
