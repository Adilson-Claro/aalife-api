package br.com.easy.aalife_api.modules.usuario.profissional.dto;

public record UsuarioProfissionalAtualizacaoRequest(String senha,
                                                    String nomeProfissional,
                                                    String email,
                                                    String telefone) {
}
