package br.com.easy.aalife_api.modules.endereco.endereco.dto;

import br.com.easy.aalife_api.modules.endereco.endereco.model.Endereco;

public record EnderecoResponse(Integer id,
                               String cidade,
                               String logradouro,
                               String bairro,
                               String cep,
                               String complemento,
                               Integer numero) {

    public static EnderecoResponse of(Endereco endereco) {
        return new EnderecoResponse(
                endereco.getId(),
                endereco.getCidade().getNome(),
                endereco.getBairro(),
                endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
    }
}
