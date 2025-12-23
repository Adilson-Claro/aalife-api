package br.com.easy.aalife_api.modules.endereco.estado.dto;

import br.com.easy.aalife_api.modules.endereco.estado.predicate.EstadoPredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoFiltros {

    private String nome;
    private String uf;

    public BooleanBuilder toPredicate() {
        return new EstadoPredicate()
                .comNome(nome)
                .comUf(uf)
                .build();
    }
}
