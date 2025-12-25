package br.com.easy.aalife_api.modules.profissao.dto;

import br.com.easy.aalife_api.modules.comum.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.modules.profissao.predicate.ProfissaoPredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfissaoFiltros {

    private String nome;
    private ETipoOrgaoRegulamentador tipoOrgaoRegulamentador;

    public BooleanBuilder toPredicate() {
        return new ProfissaoPredicate()
                .comNome(nome)
                .comOrgaoRegulamentador(tipoOrgaoRegulamentador)
                .build();
    }
}
