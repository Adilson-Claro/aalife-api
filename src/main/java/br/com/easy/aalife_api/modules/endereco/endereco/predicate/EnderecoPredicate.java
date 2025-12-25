package br.com.easy.aalife_api.modules.endereco.endereco.predicate;

import br.com.easy.aalife_api.modules.comum.predicate.PredicateBase;
import org.apache.commons.lang3.StringUtils;

import static br.com.easy.aalife_api.modules.endereco.endereco.model.QEndereco.endereco;

public class EnderecoPredicate extends PredicateBase {

    public EnderecoPredicate comCep(String cep) {
        if (!StringUtils.isBlank(cep)) {
            builder.and(endereco.cep.containsIgnoreCase(cep));
        }
        return this;
    }

    public EnderecoPredicate comCidadeNome(String cidade) {
        if (!StringUtils.isBlank(cidade)) {
            builder.and(endereco.cidade.nome.containsIgnoreCase(cidade));
        }
        return this;
    }

    public EnderecoPredicate comLogradouro(String logradouro) {
        if (!StringUtils.isBlank(logradouro)) {
            builder.and(endereco.logradouro.containsIgnoreCase(logradouro));
        }
        return this;
    }

    public EnderecoPredicate comBairro(String bairro) {
        if (!StringUtils.isBlank(bairro)) {
            builder.and(endereco.bairro.containsIgnoreCase(bairro));
        }
        return this;
    }
}
