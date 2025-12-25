package br.com.easy.aalife_api.modules.usuario.base.predicate;

import br.com.easy.aalife_api.modules.comum.predicate.PredicateBase;
import io.micrometer.common.util.StringUtils;

import static br.com.easy.aalife_api.modules.usuario.base.model.QUsuarioBase.usuarioBase;

public class UsuarioBasePredicate extends PredicateBase {

    public UsuarioBasePredicate comNome(String nome) {
        if (!StringUtils.isBlank(nome)) {
            builder.and(usuarioBase.nome.containsIgnoreCase(nome));
        }
        return this;
    }
}
