package br.com.easy.aalife_api.config.security.controller;

import br.com.easy.aalife_api.config.security.dto.AuthResponse;
import br.com.easy.aalife_api.config.security.dto.LoginRequest;
import br.com.easy.aalife_api.modules.usuario.dto.UsuarioRequest;
import br.com.easy.aalife_api.config.security.service.AuthService;
import br.com.easy.aalife_api.modules.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public void salvar(@Valid @RequestBody UsuarioRequest request) {
        usuarioService.salvar(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
