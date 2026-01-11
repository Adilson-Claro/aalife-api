package br.com.easy.aalife_api.modules.usuario.profissional.model;

import br.com.easy.aalife_api.modules.comum.enums.*;
import br.com.easy.aalife_api.modules.endereco.endereco.model.Endereco;
import br.com.easy.aalife_api.modules.profissao.model.ProfissaoEspecialidade;
import br.com.easy.aalife_api.modules.usuario.pessoa.juridica.PessoaJuridica;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalAtualizacaoRequest;
import br.com.easy.aalife_api.modules.usuario.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife_api.modules.usuario.usuariocredenciais.UsuarioCredenciais;
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
@Table(name = "usuario_profissional")
public class UsuarioProfissional extends PessoaJuridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private UsuarioCredenciais usuarioCredenciais;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conselho", nullable = false)
    private ETipoOrgaoRegulamentador tipoOrgaoRegulamentador;

    @Column(name = "nome_profissional")
    private String nomeProfissional;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "numero_conselho")
    private String numeroConselho;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profissao_especialidade_id")
    private ProfissaoEspecialidade profissaoEspecialidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_saude")
    private EAreaSaude areaSaude;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public static UsuarioProfissional of(UsuarioProfissionalRequest request, PasswordEncoder passwordEncoder) {
        var usuario = UsuarioCredenciais.builder()
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .role(ERole.PROFISSIONAL)
                .situacao(ESituacao.A)
                .tipoUsuario(ETipoUsuario.PROFISSIONAL)
                .dataCadastro(LocalDateTime.now())
                .build();

        return UsuarioProfissional.builder()
                .razaoSocial(request.razaoSocial())
                .cnpj(request.cnpj())
                .telefone(request.telefone())
                .tipoOrgaoRegulamentador(request.orgaoRegulamentador())
                .usuarioCredenciais(usuario)
                .areaSaude(request.areaSaude())
                .numeroConselho(request.numeroConselho())
                .nomeProfissional(request.nomeProfissional())
                .build();
    }

    public void editar(UsuarioProfissionalAtualizacaoRequest request) {
        applyIfNotNull(request.nomeProfissional(), this::setNomeProfissional);
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
