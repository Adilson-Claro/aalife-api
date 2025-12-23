package br.com.easy.aalife_api.modules.endereco.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRequest(@NotNull Integer profissionalId,
                              @NotNull Integer cidadeId,
                              @NotBlank String logradouro,
                              @NotBlank String bairro,
                              @NotBlank String cep,
                              @NotBlank String complemento,
                              @NotNull Integer numero) {
}
