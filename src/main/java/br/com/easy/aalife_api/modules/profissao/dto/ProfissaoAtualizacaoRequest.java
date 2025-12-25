package br.com.easy.aalife_api.modules.profissao.dto;

import br.com.easy.aalife_api.modules.comum.enums.ETipoOrgaoRegulamentador;

public record ProfissaoAtualizacaoRequest(String nome,
                                          ETipoOrgaoRegulamentador orgaoRegulamentador) {
}
