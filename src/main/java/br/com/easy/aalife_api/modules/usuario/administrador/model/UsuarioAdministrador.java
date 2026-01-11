package br.com.easy.aalife_api.modules.usuario.administrador.model;

import br.com.easy.aalife_api.modules.comum.enums.ERole;
import br.com.easy.aalife_api.modules.comum.enums.ESituacao;
import br.com.easy.aalife_api.modules.comum.enums.ETipoUsuario;
import br.com.easy.aalife_api.modules.usuario.administrador.dto.UsuarioAdministradorAtualizacaoRequest;
import br.com.easy.aalife_api.modules.usuario.usuariocredenciais.UsuarioCredenciais;
import br.com.easy.aalife_api.modules.usuario.administrador.dto.UsuarioAdministradorRequest;
import br.com.easy.aalife_api.modules.usuario.pessoa.fisica.PessoaFisica;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_administrador")
public class UsuarioAdministrador extends PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private UsuarioCredenciais usuarioCredenciais;

    public static UsuarioAdministrador of(UsuarioAdministradorRequest request, PasswordEncoder passwordEncoder) {
        var usuario = UsuarioCredenciais.builder()
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .role(ERole.ADMINISTRADOR)
                .situacao(ESituacao.A)
                .tipoUsuario(ETipoUsuario.ADMINISTRADOR)
                .dataCadastro(LocalDateTime.now())
                .build();

        return UsuarioAdministrador.builder()
                .cpf(request.cpf())
                .nome(request.nome())
                .telefone(request.telefone())
                .usuarioCredenciais(usuario)
                .build();
    }

    public void editar(UsuarioAdministradorAtualizacaoRequest request) {
        applyIfNotNull(request.nome(), this::setNome);
        applyIfNotNull(request.telefone(), this::setTelefone);
        applyIfNotNull(request.email(),
                email -> this.usuarioCredenciais.setEmail(email));
    }

    public void editarSenha(String senha, PasswordEncoder passwordEncoder) {
        this.usuarioCredenciais.setSenha(passwordEncoder.encode(senha));
    }

    public void alterarSituacao() {
        this.usuarioCredenciais.setSituacao(
                this.usuarioCredenciais.getSituacao() == ESituacao.A ? ESituacao.I : ESituacao.A
        );
    }

    private <T> void applyIfNotNull(T value, Consumer<T> setter) {
        Optional.ofNullable(value).ifPresent(setter);
    }
}
