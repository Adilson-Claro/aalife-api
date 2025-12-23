package br.com.easy.aalife_api.modules.usuario.profissional.dto;

import br.com.easy.aalife_api.comum.enums.EAreaSaude;
import br.com.easy.aalife_api.comum.enums.ETipoOrgaoRegulamentador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioProfissionalRequest(String razaoSocial,
                                         String cnpj,
                                         @NotBlank String senha,
                                         @NotBlank String email,
                                         @NotBlank String telefone,
                                         @NotNull ETipoOrgaoRegulamentador orgaoRegulamentador,
                                         String nomeProfissional,
                                         String numeroConselho,
                                         @NotNull EAreaSaude areaSaude,
                                         Integer enderecoId) {
}
