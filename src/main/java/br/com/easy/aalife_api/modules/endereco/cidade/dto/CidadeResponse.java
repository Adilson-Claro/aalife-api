package br.com.easy.aalife_api.modules.endereco.cidade.dto;

import br.com.easy.aalife_api.modules.endereco.cidade.model.Cidade;

public record CidadeResponse(String nome,
                             String codigo) {

    public static CidadeResponse of(Cidade cidade) {
        return new CidadeResponse(
                cidade.getNome(),
                cidade.getCodigoIbge()
        );
    }
}
