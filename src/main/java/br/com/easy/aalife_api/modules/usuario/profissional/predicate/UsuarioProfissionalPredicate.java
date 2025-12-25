package br.com.easy.aalife_api.modules.usuario.profissional.predicate;

import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.modules.comum.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.modules.comum.predicate.PredicateBase;
import io.micrometer.common.util.StringUtils;

import static br.com.easy.aalife_api.modules.usuario.profissional.model.QUsuarioProfissional.usuarioProfissional;

public class UsuarioProfissionalPredicate extends PredicateBase {

    public UsuarioProfissionalPredicate comId(Integer id) {
        if (id != null) {
            builder.and(usuarioProfissional.id.eq(id));
        }
        return this;
    }

    public UsuarioProfissionalPredicate comSituacao(ESituacao situacao) {
        if (situacao != null) {
            builder.and(usuarioProfissional.usuarioCredenciais.situacao.eq(situacao));
        }
        return this;
    }

    public UsuarioProfissionalPredicate comTipoOrgaoRegulamentador(ETipoOrgaoRegulamentador tipoOrgaoRegulamentador) {
        if (tipoOrgaoRegulamentador != null) {
            builder.and(usuarioProfissional.tipoOrgaoRegulamentador.eq(tipoOrgaoRegulamentador));
        }
        return this;
    }

    public UsuarioProfissionalPredicate comNomeProfissional(String nome) {
        if (StringUtils.isNotBlank(nome)) {
            builder.and(usuarioProfissional.nomeProfissional.containsIgnoreCase(nome));
        }
        return this;
    }
}
