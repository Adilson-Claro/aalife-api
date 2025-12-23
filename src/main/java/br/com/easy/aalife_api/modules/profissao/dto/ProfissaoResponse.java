package br.com.easy.aalife_api.modules.profissao.dto;

import br.com.easy.aalife_api.comum.enums.ESituacao;
import br.com.easy.aalife_api.comum.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.modules.profissao.model.Profissao;

public record ProfissaoResponse(String nome,
                                ETipoOrgaoRegulamentador orgaoRegulamentador,
                                ESituacao situacao) {

    public static ProfissaoResponse of(Profissao profissao) {
        return new ProfissaoResponse(
                profissao.getNome(),
                profissao.getOrgaoRegulamentador(),
                profissao.getSituacao()
        );
    }
}
