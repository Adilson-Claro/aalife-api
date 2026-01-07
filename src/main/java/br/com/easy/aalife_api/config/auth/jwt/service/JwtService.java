package br.com.easy.aalife_api.config.auth.jwt.service;

import br.com.easy.aalife_api.config.auth.UsuarioAutenticado;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static br.com.easy.aalife_api.modules.comum.utils.ConstantsUtils.VINTE_E_QUATRO;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secret;

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(UsuarioAutenticado user) {
        var now = Instant.now();
        var expiration = now.plus(VINTE_E_QUATRO, ChronoUnit.HOURS);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .claim("tipoUsuario", user.getTipoUsuario().name())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValido(String token, UserDetails user) {
        return parse(token).getSubject().equals(user.getUsername());
    }
}
