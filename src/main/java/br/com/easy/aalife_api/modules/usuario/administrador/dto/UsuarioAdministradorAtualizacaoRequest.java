package br.com.easy.aalife_api.modules.usuario.administrador.dto;

import br.com.easy.aalife_api.modules.comum.annotations.ValidarSenha;

public record UsuarioAdministradorAtualizacaoRequest(String nome,
                                                     String email,
                                                     String telefone,
                                                     @ValidarSenha String senha) {
}
