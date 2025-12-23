package br.com.easy.aalife_api.modules.profissao.predicate;

import br.com.easy.aalife_api.comum.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.comum.predicate.PredicateBase;
import io.micrometer.common.util.StringUtils;

import static br.com.easy.aalife_api.modules.profissao.model.QProfissao.profissao;

public class ProfissaoPredicate extends PredicateBase {

    public ProfissaoPredicate comNome(String nome) {
        if (!StringUtils.isBlank(nome)) {
            builder.and(profissao.nome.containsIgnoreCase(nome));
        }
        return this;
    }

    public ProfissaoPredicate comOrgaoRegulamentador(ETipoOrgaoRegulamentador tipoOrgaoRegulamentador) {
        if (tipoOrgaoRegulamentador != null) {
            builder.and(profissao.orgaoRegulamentador.eq(tipoOrgaoRegulamentador));
        }
        return this;
    }
}
