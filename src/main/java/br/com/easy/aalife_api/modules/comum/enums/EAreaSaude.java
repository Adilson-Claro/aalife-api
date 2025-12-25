package br.com.easy.aalife_api.modules.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EAreaSaude {

    MEDICINA("Medicina"),
    ENFERMAGEM("Enfermagem"),
    ODONTOLOGIA("Odontologia"),
    FARMACIA("Farmácia"),
    FISIOTERAPIA("Fisioterapia"),
    PSICOLOGIA("Psicologia"),
    EDUCACAO_FISICA("Educação Física"),
    NUTRICAO("Nutrição"),
    BIOMEDICINA("Biomedicina"),
    RADIOLOGIA("Radiologia");

    private final String descricao;
}
