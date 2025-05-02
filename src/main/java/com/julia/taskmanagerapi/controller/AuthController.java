package com.julia.taskmanagerapi.controller;

import com.julia.taskmanagerapi.dto.LoginDTO;
import com.julia.taskmanagerapi.dto.RegistroDTO;
import com.julia.taskmanagerapi.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroDTO registroDTO) {
        return ResponseEntity.ok(usuarioService.register(registroDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(usuarioService.login(loginDTO));
    }
}
