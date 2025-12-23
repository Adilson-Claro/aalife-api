package br.com.easy.aalife_api.modules.especialidade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EspecialidadeRequest(@NotBlank String nome,
                                   Integer profissaoId) {
}
