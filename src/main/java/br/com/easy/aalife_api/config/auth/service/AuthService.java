package br.com.easy.aalife_api.config.auth.service;

import br.com.easy.aalife_api.config.auth.UsuarioAutenticado;
import br.com.easy.aalife_api.config.auth.dto.LoginRequest;
import br.com.easy.aalife_api.config.auth.dto.TokenResponse;
import br.com.easy.aalife_api.config.auth.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.VINTE_E_QUATRO;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenResponse autenticar(LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()));

        var user = (UsuarioAutenticado) authentication.getPrincipal();
        var token = jwtService.gerarToken(user);

        return new TokenResponse(token, "Bearer", VINTE_E_QUATRO);
    }
}
