package br.com.easy.aalife_api.modules.endereco.cidade.dto;

import br.com.easy.aalife_api.modules.endereco.cidade.predicate.CidadePredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeFiltros {

    private String nome;
    private String codigoIbge;

    public BooleanBuilder toPredicate() {
        return new CidadePredicate()
                .comNome(nome)
                .comCodigoIbge(codigoIbge)
                .build();
    }
}
