package br.com.easy.aalife_api.security.controller;

import br.com.easy.aalife_api.security.dto.AuthResponse;
import br.com.easy.aalife_api.security.dto.LoginRequest;
import br.com.easy.aalife_api.security.dto.UsuarioRegistroRequest;
import br.com.easy.aalife_api.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody UsuarioRegistroRequest request) {
        return authService.registrar(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
