package com.julia.taskmanagerapi.dto;

import com.julia.taskmanagerapi.model.Prioridade;
import com.julia.taskmanagerapi.model.Status;

import java.time.LocalDate;

public class TaskResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private Prioridade prioridade;
    private Status status;

    public TaskResponseDTO() {
    }

    public TaskResponseDTO(Long id, String titulo, String descricao, LocalDate prazo, Prioridade prioridade, Status status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}