package br.com.easy.aalife_api.modules.endereco.cidade.dto;

public record CidadeRequest(String nome,
                            Integer estadoId,
                            String codigoIbge) {
}
