package com.julia.taskmanagerapi.service;

import com.julia.taskmanagerapi.dto.AuthResponseDTO;
import com.julia.taskmanagerapi.dto.LoginDTO;
import com.julia.taskmanagerapi.dto.RegistroDTO;
import com.julia.taskmanagerapi.model.Usuario;

public interface UsuarioService {
    Usuario register(RegistroDTO registroDTO);
    AuthResponseDTO login(LoginDTO loginDTO);
} 