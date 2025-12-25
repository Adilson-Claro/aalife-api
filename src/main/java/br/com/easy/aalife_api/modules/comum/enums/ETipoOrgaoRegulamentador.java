package br.com.easy.aalife_api.modules.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETipoOrgaoRegulamentador {

    CRM("Conselho Regional de Medicina"),
    COREN("Conselho Regional de Enfermagem"),
    CRO("Conselho Regional de Odontologia"),
    CRF("Conselho Regional de Farmácia"),
    CREFITO("Conselho Regional de Fisioterapia e Terapia Ocupacional"),
    CRP("Conselho Regional de Psicologia"),
    CREF("Conselho Regional de Educação Física"),
    CRN("Conselho Regional de Nutrição"),
    CFBM("Conselho Federal de Biomedicina"),
    CRTR("Conselho Regional de Técnicos em Radiologia");

    private final String descricao;
}
