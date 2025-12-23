package br.com.easy.aalife_api.modules.endereco.endereco.dto;

import br.com.easy.aalife_api.modules.endereco.endereco.predicate.EnderecoPredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoFiltros {

    private String cidadeNome;
    private String bairro;
    private String cep;
    private String logradouro;

    public BooleanBuilder toPredicate() {
        return new EnderecoPredicate()
                .comCidadeNome(cidadeNome)
                .comBairro(bairro)
                .comCep(cep)
                .comLogradouro(logradouro)
                .build();
    }
}
