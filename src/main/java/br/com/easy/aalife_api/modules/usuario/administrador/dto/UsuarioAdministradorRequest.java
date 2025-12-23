package br.com.easy.aalife_api.modules.usuario.administrador.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioAdministradorRequest(@NotBlank String cpf,
                                          @NotBlank String nome,
                                          @NotBlank String telefone,
                                          @NotBlank String email,
                                          @NotBlank String senha) {
}
