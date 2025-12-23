package br.com.easy.aalife_api.modules.endereco.estado.dto;

import br.com.easy.aalife_api.modules.endereco.estado.model.Estado;

public record EstadoResponse(Integer id,
                             String nome,
                             String uf) {

    public static EstadoResponse of(Estado estado) {
        return new EstadoResponse(
                estado.getId(),
                estado.getNome(),
                estado.getUf()
        );
    }
}
