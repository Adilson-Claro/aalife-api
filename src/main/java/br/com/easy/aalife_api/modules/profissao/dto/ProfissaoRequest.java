package br.com.easy.aalife_api.modules.profissao.dto;

import br.com.easy.aalife_api.modules.comum.enums.EAreaSaude;
import br.com.easy.aalife_api.modules.comum.enums.ETipoOrgaoRegulamentador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfissaoRequest(@NotBlank String nome,
                               @NotNull EAreaSaude areaSaude,
                               @NotNull ETipoOrgaoRegulamentador orgaoRegulamentador) {
}
