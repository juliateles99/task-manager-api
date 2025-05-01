package com.julia.taskmanagerapi.dto;

import com.julia.taskmanagerapi.model.Prioridade;
import com.julia.taskmanagerapi.model.Status;

import java.time.LocalDate;

public record TaskResponseDTO(
        Long id,
        String titulo,
        String descricao,
        LocalDate prazo,
        Prioridade prioridade,
        Status status
) {}