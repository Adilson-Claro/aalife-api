package br.com.easy.aalife_api.config.security.service;

import br.com.easy.aalife_api.config.security.dto.AuthResponse;
import br.com.easy.aalife_api.config.security.dto.LoginRequest;
import br.com.easy.aalife_api.modules.usuario.model.Usuario;
import br.com.easy.aalife_api.modules.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UsuarioService service;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.senha()));

        var usuario = service.buscarUsuarioPorEmail(request.email());
        return gerarTokens(usuario);
    }

    private AuthResponse gerarTokens(Usuario usuario) {
        var accessToken = jwtService.generateAccessToken(usuario);
        var refreshToken = jwtService.generateRefreshToken(usuario);
        return AuthResponse.of(accessToken, refreshToken);
    }
}
