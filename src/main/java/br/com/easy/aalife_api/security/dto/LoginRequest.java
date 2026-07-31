package br.com.easy.aalife_api.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank @Email String email,
                           @NotBlank String senha) {
}
