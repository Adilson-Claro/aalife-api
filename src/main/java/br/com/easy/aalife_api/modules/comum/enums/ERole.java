package br.com.easy.aalife_api.modules.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERole {

    ADMINISTRADOR("Administrador"),
    BASE("Usuário comum"),
    PROFISSIONAL("Usuário profissional");

    private final String descricao;
}
