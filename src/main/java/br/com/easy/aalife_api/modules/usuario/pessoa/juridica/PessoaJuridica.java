package br.com.easy.aalife_api.modules.usuario.pessoa.juridica;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class PessoaJuridica {

    private String telefone;
    private String razaoSocial;
    private String cnpj;
}
