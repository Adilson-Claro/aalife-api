package br.com.easy.aalife_api.modules.especialidade.predicate;

import br.com.easy.aalife_api.modules.comum.predicate.PredicateBase;
import io.micrometer.common.util.StringUtils;

import static br.com.easy.aalife_api.modules.especialidade.model.QEspecialidade.especialidade;

public class EspecialidadePredicate extends PredicateBase {

    public EspecialidadePredicate comNome(String nome) {
        if (!StringUtils.isBlank(nome)) {
            builder.and(especialidade.nome.containsIgnoreCase(nome));
        }
        return this;
    }
}
