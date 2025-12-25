package br.com.easy.aalife_api.modules.comum.validator;

import br.com.easy.aalife_api.modules.comum.enums.EAreaSaude;
import br.com.easy.aalife_api.modules.comum.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.config.exceptions.ValidationException;

import java.util.Map;
import java.util.Set;

public class AreaSaudeValidator {

    private static final Map<EAreaSaude, Set<ETipoOrgaoRegulamentador>> ORGAOS_POR_AREA =
            Map.of(
                    EAreaSaude.MEDICINA, Set.of(ETipoOrgaoRegulamentador.CRM),
                    EAreaSaude.ENFERMAGEM, Set.of(ETipoOrgaoRegulamentador.COREN),
                    EAreaSaude.ODONTOLOGIA, Set.of(ETipoOrgaoRegulamentador.CRO),
                    EAreaSaude.FARMACIA, Set.of(ETipoOrgaoRegulamentador.CRF),
                    EAreaSaude.FISIOTERAPIA, Set.of(ETipoOrgaoRegulamentador.CREFITO),
                    EAreaSaude.PSICOLOGIA, Set.of(ETipoOrgaoRegulamentador.CRP),
                    EAreaSaude.EDUCACAO_FISICA, Set.of(ETipoOrgaoRegulamentador.CREF),
                    EAreaSaude.NUTRICAO, Set.of(ETipoOrgaoRegulamentador.CRN),
                    EAreaSaude.BIOMEDICINA, Set.of(ETipoOrgaoRegulamentador.CFBM),
                    EAreaSaude.RADIOLOGIA, Set.of(ETipoOrgaoRegulamentador.CRTR)
            );

    public static void validarOrgaoRegulamentador(EAreaSaude area, ETipoOrgaoRegulamentador orgao) {
        var orgaosPermitidos = ORGAOS_POR_AREA.get(area);

        if (orgaosPermitidos == null || !orgaosPermitidos.contains(orgao)) {
            throw new ValidationException("Órgão regulamentador inválido para a área da saúde informada.");
        }
    }
}
