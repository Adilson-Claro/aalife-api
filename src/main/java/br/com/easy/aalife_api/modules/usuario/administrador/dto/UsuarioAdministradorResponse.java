package br.com.easy.aalife_api.modules.usuario.administrador.dto;

import br.com.easy.aalife_api.modules.comum.enums.ESituacao;

public record UsuarioAdministradorResponse(ESituacao situacao,
                                           String nome,
                                           String email) {
}
