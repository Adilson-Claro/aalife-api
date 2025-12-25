package br.com.easy.aalife_api.modules.usuario.usuariocredenciais;

import br.com.easy.aalife_api.modules.comum.enums.ERole;
import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.modules.comum.enums.ETipoUsuario;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCredenciais {

    private String email;
    private String senha;
    private ERole role;
    private ESituacao situacao;
    private ETipoUsuario tipoUsuario;
    private LocalDateTime dataCadastro;
}
