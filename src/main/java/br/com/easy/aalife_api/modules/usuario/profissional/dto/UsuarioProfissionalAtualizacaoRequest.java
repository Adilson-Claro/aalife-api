package br.com.easy.aalife_api.modules.usuario.profissional.dto;

import br.com.easy.aalife_api.modules.comum.annotations.ValidarSenha;

public record UsuarioProfissionalAtualizacaoRequest(@ValidarSenha String senha,
                                                    String nomeProfissional,
                                                    String email,
                                                    String telefone) {
}
