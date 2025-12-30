package br.com.easy.aalife_api.config.auth.dto;

public record TokenResponse(String accessToken,
                            String tokenType,
                            long expiresIn) {
}
