package br.com.easy.aalife_api.config.security.controller;

import br.com.easy.aalife_api.config.security.dto.AuthResponse;
import br.com.easy.aalife_api.config.security.dto.LoginRequest;
import br.com.easy.aalife_api.config.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
