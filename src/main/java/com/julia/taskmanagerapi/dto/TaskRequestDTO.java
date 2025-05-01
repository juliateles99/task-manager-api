package com.julia.taskmanagerapi.dto;

import com.julia.taskmanagerapi.model.Prioridade;
import com.julia.taskmanagerapi.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskRequestDTO(
        @NotBlank String titulo,
        String descricao,
        @NotNull LocalDate prazo,
        @NotNull Prioridade prioridade,
        @NotNull Status status
) {}