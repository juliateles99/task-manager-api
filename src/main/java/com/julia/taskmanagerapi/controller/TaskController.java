package com.julia.taskmanagerapi.controller;

import com.julia.taskmanagerapi.dto.TaskRequestDTO;
import com.julia.taskmanagerapi.dto.TaskResponseDTO;
import com.julia.taskmanagerapi.model.Prioridade;
import com.julia.taskmanagerapi.model.Status;
import com.julia.taskmanagerapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Criar nova tarefa")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody TaskRequestDTO dto) {
        return new ResponseEntity<>(taskService.create(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas as tarefas")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @Operation(summary = "Buscar tarefa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @Operation(summary = "Atualizar tarefa existente")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO dto) {
        return ResponseEntity.ok(taskService.update(id, dto));
    }

    @Operation(summary = "Excluir tarefa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Filtrar tarefas por status")
    @GetMapping("/status")
    public ResponseEntity<List<TaskResponseDTO>> findByStatus(
            @Parameter(description = "Status da tarefa") @RequestParam Status status) {
        return ResponseEntity.ok(taskService.findByStatus(status));
    }

    @Operation(summary = "Filtrar tarefas por prioridade")
    @GetMapping("/prioridade")
    public ResponseEntity<List<TaskResponseDTO>> findByPrioridade(
            @Parameter(description = "Prioridade da tarefa") @RequestParam Prioridade prioridade) {
        return ResponseEntity.ok(taskService.findByPrioridade(prioridade));
    }

    @Operation(summary = "Filtrar tarefas por intervalo de datas")
    @GetMapping("/prazo")
    public ResponseEntity<List<TaskResponseDTO>> findByPrazoBetween(
            @Parameter(description = "Data inicial") @RequestParam LocalDate inicio,
            @Parameter(description = "Data final") @RequestParam LocalDate fim) {
        return ResponseEntity.ok(taskService.findByPrazoBetween(inicio, fim));
    }
}
