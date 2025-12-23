package br.com.easy.aalife_api.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ESexo {

    M("Masculino"),
    F("Feminino");

    private String descricao;
}
