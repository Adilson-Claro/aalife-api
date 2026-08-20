package br.com.easy.aalife_api.config.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    @Value("${jwt.refresh-expiration-ms}")
    private long refreshExpirationMs;

    private SecretKey signingKey;

    @PostConstruct
    void init() {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(UserDetails usuario) {
        return buildToken(usuario, expirationMs);
    }

    public String generateRefreshToken(UserDetails usuario) {
        return buildToken(usuario, refreshExpirationMs);
    }

    private String buildToken(UserDetails usuario, long expiration) {
        var agora = new Date();
        var expiraEm = new Date(agora.getTime() + expiration);

        return Jwts.builder()
                .subject(usuario.getUsername())
                .issuedAt(agora)
                .expiration(expiraEm)
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValido(String token, UserDetails usuario) {
        var username = extractUsername(token);
        return username.equals(usuario.getUsername()) && !isTokenExpirado(token);
    }

    private boolean isTokenExpirado(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        var claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }
}
