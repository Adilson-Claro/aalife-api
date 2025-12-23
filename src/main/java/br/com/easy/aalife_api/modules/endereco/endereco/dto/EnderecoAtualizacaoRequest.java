package br.com.easy.aalife_api.modules.endereco.endereco.dto;

public record EnderecoAtualizacaoRequest(Integer cidadeId,
                                         String logradouro,
                                         String bairro,
                                         String cep,
                                         String complemento,
                                         Integer numero) {
}
