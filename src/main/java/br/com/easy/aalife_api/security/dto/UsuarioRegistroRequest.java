package br.com.easy.aalife_api.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRegistroRequest(@NotBlank String nome,
                                     @NotBlank String email,
                                     @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
                                     @NotBlank String senha) {
}
