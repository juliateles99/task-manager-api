package com.julia.taskmanagerapi.service.impl;

import com.julia.taskmanagerapi.dto.AuthResponseDTO;
import com.julia.taskmanagerapi.dto.LoginDTO;
import com.julia.taskmanagerapi.dto.RegistroDTO;
import com.julia.taskmanagerapi.model.Usuario;
import com.julia.taskmanagerapi.repository.UsuarioRepository;
import com.julia.taskmanagerapi.security.JwtService;
import com.julia.taskmanagerapi.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Usuario register(RegistroDTO registroDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(registroDTO.getNome());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setSenha(passwordEncoder.encode(registroDTO.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getSenha())
        );
        var usuario = usuarioRepository.findByUsername(loginDTO.getUsername())
            .orElseThrow();
        var token = jwtService.generateToken(usuario);
        return new AuthResponseDTO(token);
    }
} 