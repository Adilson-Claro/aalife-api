package br.com.easy.aalife_api.modules.usuario.profissional.dto;

import br.com.easy.aalife_api.comum.enums.EAreaSaude;
import br.com.easy.aalife_api.comum.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.modules.usuario.profissional.model.UsuarioProfissional;

public record UsuarioProfissionalResponse(String nomeProfissional,
                                          String telefone,
                                          String email,
                                          ETipoOrgaoRegulamentador tipoConselho,
                                          String cnpj,
                                          EAreaSaude areaSaude) {

    public static UsuarioProfissionalResponse of(UsuarioProfissional profissional) {
        return new UsuarioProfissionalResponse(
                profissional.getNomeProfissional(),
                profissional.getTelefone(),
                profissional.getUsuarioCredenciais().getEmail(),
                profissional.getTipoOrgaoRegulamentador(),
                profissional.getTelefone(),
                profissional.getAreaSaude()
        );
    }
}
