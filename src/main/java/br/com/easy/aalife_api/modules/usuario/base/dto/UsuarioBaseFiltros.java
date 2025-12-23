package br.com.easy.aalife_api.modules.usuario.base.dto;

import br.com.easy.aalife_api.modules.usuario.base.predicate.UsuarioBasePredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioBaseFiltros {

    private String nome;

    public BooleanBuilder toPredicate() {
        return new UsuarioBasePredicate()
                .comNome(nome)
                .build();
    }
}
