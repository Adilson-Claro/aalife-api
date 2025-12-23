package br.com.easy.aalife_api.modules.especialidade.dto;

import br.com.easy.aalife_api.comum.enums.ESituacao;
import br.com.easy.aalife_api.modules.especialidade.model.Especialidade;

public record EspecialidadeResponse(String nome,
                                    ESituacao situacao,
                                    Integer profissaoId) {

    public static EspecialidadeResponse of(Especialidade especialidade) {
        return new EspecialidadeResponse(
                especialidade.getNome(),
                especialidade.getSituacao(),
                especialidade.getProfissaoId().getId()
        );
    }
}
