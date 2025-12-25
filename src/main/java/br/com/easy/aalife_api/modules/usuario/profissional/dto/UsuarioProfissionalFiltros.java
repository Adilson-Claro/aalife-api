package br.com.easy.aalife_api.modules.usuario.profissional.dto;

import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.modules.comum.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.modules.usuario.profissional.predicate.UsuarioProfissionalPredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioProfissionalFiltros {

    private Integer id;
    private ESituacao situacao;
    private ETipoOrgaoRegulamentador tipoConselho;
    private String nomeProfissional;

    public BooleanBuilder toPredicate() {
        return new UsuarioProfissionalPredicate()
                .comId(id)
                .comSituacao(situacao)
                .comTipoOrgaoRegulamentador(tipoConselho)
                .comNomeProfissional(nomeProfissional)
                .build();
    }
}
