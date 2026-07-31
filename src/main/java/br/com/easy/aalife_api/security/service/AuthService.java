package br.com.easy.aalife_api.security.service;

import br.com.easy.aalife_api.security.dto.AuthResponse;
import br.com.easy.aalife_api.security.dto.LoginRequest;
import br.com.easy.aalife_api.security.dto.UsuarioRegistroRequest;
import br.com.easy.aalife_api.security.enums.ERole;
import br.com.easy.aalife_api.security.model.Usuario;
import br.com.easy.aalife_api.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registrar(UsuarioRegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(CONFLICT, "E-mail já cadastrado");
        }

        var usuario = Usuario.of(request, passwordEncoder.encode(request.senha()));
        usuarioRepository.save(usuario);
        return gerarTokens(usuario);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        var usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow();

        return gerarTokens(usuario);
    }

    private AuthResponse gerarTokens(Usuario usuario) {
        var accessToken = jwtService.generateAccessToken(usuario);
        var refreshToken = jwtService.generateRefreshToken(usuario);
        return AuthResponse.of(accessToken, refreshToken);
    }
}
