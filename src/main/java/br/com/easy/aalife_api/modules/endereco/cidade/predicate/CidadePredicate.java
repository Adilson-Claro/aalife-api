package br.com.easy.aalife_api.modules.endereco.cidade.predicate;

import br.com.easy.aalife_api.comum.predicate.PredicateBase;
import org.apache.commons.lang3.StringUtils;

import static br.com.easy.aalife_api.modules.cidade.model.QCidade.cidade;

public class CidadePredicate extends PredicateBase {

    public CidadePredicate comNome(String nome) {
        if (!StringUtils.isBlank(nome)) {
            builder.and(cidade.nome.containsIgnoreCase(nome));
        }
        return this;
    }

    public CidadePredicate comCodigoIbge(String codigoIbge) {
        if (!StringUtils.isBlank(codigoIbge)) {
            builder.and(cidade.codigoIbge.containsIgnoreCase(codigoIbge));
        }
        return this;
    }
}
