package br.com.easy.aalife_api.modules.endereco.estado.predicate;

import br.com.easy.aalife_api.comum.predicate.PredicateBase;
import io.micrometer.common.util.StringUtils;

import static br.com.easy.aalife_api.modules.estado.model.QEstado.estado;

public class EstadoPredicate extends PredicateBase {

    public EstadoPredicate comNome(String nome) {
        if (!StringUtils.isBlank(nome)) {
            builder.and(estado.nome.containsIgnoreCase(nome));
        }
        return this;
    }

    public EstadoPredicate comUf(String uf) {
        if (!StringUtils.isBlank(uf)) {
            builder.and(estado.uf.containsIgnoreCase(uf));
        }
        return this;
    }
}
