package br.com.easy.aalife_api.modules.usuario.dto;

import br.com.easy.aalife_api.modules.usuario.enums.EAreaSaude;
import br.com.easy.aalife_api.modules.usuario.enums.ETipoOrgaoRegulamentador;
import br.com.easy.aalife_api.modules.usuario.enums.ETipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioRequest(@NotBlank String nome,
                             @NotBlank String email,
                             @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
                             @NotBlank String senha,
                             ETipoOrgaoRegulamentador tipoOrgaoRegulamentador,
                             @CPF String cpf,
                             @CNPJ String cnpj,
                             String razaoSocial,
                             LocalDate dataNascimento,
                             EAreaSaude areaSaude,
                             Integer numeroOrgaoRegulamentador,
                             Double altura,
                             Double peso,
                             ETipoUsuario tipoUsuario) {
}
