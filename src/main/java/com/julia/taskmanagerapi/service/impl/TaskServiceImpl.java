package com.julia.taskmanagerapi.service.impl;

import com.julia.taskmanagerapi.dto.TaskRequestDTO;
import com.julia.taskmanagerapi.dto.TaskResponseDTO;
import com.julia.taskmanagerapi.exception.TaskNotFoundException;
import com.julia.taskmanagerapi.mapper.TaskMapper;
import com.julia.taskmanagerapi.model.Prioridade;
import com.julia.taskmanagerapi.model.Status;
import com.julia.taskmanagerapi.model.Tarefa;
import com.julia.taskmanagerapi.model.Usuario;
import com.julia.taskmanagerapi.repository.TaskRepository;
import com.julia.taskmanagerapi.service.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskServiceImpl(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tarefa tarefa = mapper.toEntity(taskRequestDTO);
        tarefa.setUsuario(usuario);
        tarefa = repository.save(tarefa);
        return mapper.toDTO(tarefa);
    }

    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tarefa tarefa = repository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));
        
        Tarefa tarefaAtualizada = mapper.toEntity(taskRequestDTO);
        tarefaAtualizada.setId(tarefa.getId());
        tarefaAtualizada.setUsuario(usuario);
        
        tarefaAtualizada = repository.save(tarefaAtualizada);
        return mapper.toDTO(tarefaAtualizada);
    }

    @Override
    public TaskResponseDTO getTaskById(Long id) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tarefa tarefa = repository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));
        return mapper.toDTO(tarefa);
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByUsuario(usuario)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tarefa tarefa = repository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));
        repository.delete(tarefa);
    }

    @Override
    public List<TaskResponseDTO> findByStatus(Status status) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByUsuarioAndStatus(usuario, status)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDTO> findByPrioridade(Prioridade prioridade) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByUsuarioAndPrioridade(usuario, prioridade)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDTO> findByPrazoBetween(LocalDate inicio, LocalDate fim) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByUsuarioAndPrazoBetween(usuario, inicio, fim)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
