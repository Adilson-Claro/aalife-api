package br.com.easy.aalife_api.modules.usuario.base.model;

import br.com.easy.aalife_api.comum.enums.ERole;
import br.com.easy.aalife_api.comum.enums.ESexo;
import br.com.easy.aalife_api.comum.enums.ESituacao;
import br.com.easy.aalife_api.comum.enums.ETipoUsuario;
import br.com.easy.aalife_api.modules.usuario.usuariocredenciais.UsuarioCredenciais;
import br.com.easy.aalife_api.modules.usuario.base.dto.UsuarioBaseRequest;
import br.com.easy.aalife_api.modules.usuario.pessoa.fisica.PessoaFisica;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_base")
public class UsuarioBase extends PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private UsuarioCredenciais usuarioCredenciais;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private ESexo sexo;

    public static UsuarioBase of(UsuarioBaseRequest request, PasswordEncoder passwordEncoder) {
        var usuario = UsuarioCredenciais.builder()
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .role(ERole.BASE)
                .situacao(ESituacao.A)
                .tipoUsuario(ETipoUsuario.BASE)
                .dataCadastro(LocalDateTime.now())
                .build();

        return UsuarioBase.builder()
                .nome(request.nome())
                .peso(request.peso())
                .altura(request.altura())
                .idade(request.idade())
                .dataNascimento(request.dataNascimento())
                .usuarioCredenciais(usuario)
                .sexo(request.sexo())
                .build();
    }

    public void editar(UsuarioBaseRequest request, PasswordEncoder passwordEncoder) {
        this.setNome(request.nome());
        this.usuarioCredenciais.setEmail(request.email());
        this.setAltura(request.altura());
        this.setPeso(request.peso());
        this.setIdade(request.idade());
        this.setDataNascimento(request.dataNascimento());
        this.usuarioCredenciais.setSenha(passwordEncoder.encode(request.senha()));
    }

    public void alterarSituacao() {
        this.usuarioCredenciais.setSituacao(this.usuarioCredenciais.getSituacao() == ESituacao.A ? ESituacao.I : ESituacao.A);
    }
}
