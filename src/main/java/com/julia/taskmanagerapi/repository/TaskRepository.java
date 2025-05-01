package com.julia.taskmanagerapi.repository;

import com.julia.taskmanagerapi.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatus(String status);
    List<Tarefa> findByPrioridade(String prioridade);
    List<Tarefa> findByPrazoBetween(LocalDate start, LocalDate end);
}