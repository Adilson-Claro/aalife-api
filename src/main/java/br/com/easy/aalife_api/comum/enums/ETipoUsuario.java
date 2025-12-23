package br.com.easy.aalife_api.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETipoUsuario {

    BASE("Usuario comum"),
    ADMINISTRADOR("Administrador"),
    PROFISSIONAL("Profissional de saude");

    private final String descricao;
}
