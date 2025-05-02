package com.julia.taskmanagerapi.repository;

import com.julia.taskmanagerapi.model.Prioridade;
import com.julia.taskmanagerapi.model.Status;
import com.julia.taskmanagerapi.model.Tarefa;
import com.julia.taskmanagerapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuario(Usuario usuario);
    Optional<Tarefa> findByIdAndUsuario(Long id, Usuario usuario);
    List<Tarefa> findByUsuarioAndStatus(Usuario usuario, Status status);
    List<Tarefa> findByUsuarioAndPrioridade(Usuario usuario, Prioridade prioridade);
    List<Tarefa> findByUsuarioAndPrazoBetween(Usuario usuario, LocalDate inicio, LocalDate fim);
}