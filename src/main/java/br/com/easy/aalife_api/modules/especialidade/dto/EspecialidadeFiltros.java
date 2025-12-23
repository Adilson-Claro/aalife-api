package br.com.easy.aalife_api.modules.especialidade.dto;


import br.com.easy.aalife_api.modules.especialidade.predicate.EspecialidadePredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadeFiltros {

    private String nome;

    public BooleanBuilder toPredicate() {
        return new EspecialidadePredicate()
                .comNome(nome)
                .build();
    }
}
